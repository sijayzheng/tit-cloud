package cn.sijay.system.service.impl;

import cn.sijay.common.core.entity.Id;
import cn.sijay.common.core.entity.SelectOption;
import cn.sijay.common.core.enums.ExceptionEnum;
import cn.sijay.common.core.exception.BaseException;
import cn.sijay.common.core.utils.BeanUtil;
import cn.sijay.common.core.utils.StringUtil;
import cn.sijay.common.mybatis.entity.PageQuery;
import cn.sijay.common.web.utils.ExcelUtil;
import cn.sijay.system.dto.SysDictDataDto;
import cn.sijay.system.entity.SysDictData;
import cn.sijay.system.mapper.SysDictDataMapper;
import cn.sijay.system.service.ISysDictDataService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <strong>SysDictDataService</strong>
 * <p>
 * 字典数据服务层实现类
 * </p>
 *
 * @author sijay
 * @since 2024-04-28
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SysDictDataServiceImpl extends ServiceImpl<SysDictDataMapper, SysDictData> implements ISysDictDataService {

    /**
     * 分页查询
     *
     * @param dto       数据传输对象
     * @param pageQuery 分页查询条件
     * @return 分页查询结果
     */
    @Override
    public IPage<SysDictDataDto> page(SysDictDataDto dto, PageQuery pageQuery) {
        return page(pageQuery.build(), buildQueryWrapper(dto, SysDictDataDto.class)).convert(SysDictData::toSysDictDataDto);
    }

    /**
     * 新增
     *
     * @param dto 数据传输对象
     * @return 新增结果，true为成功，false为失败
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean add(SysDictDataDto dto) {
        checkUnique(dto);
        return save(dto.toSysDictData());
    }

    /**
     * 更新
     *
     * @param dto 数据传输对象
     * @return 更新结果，true为成功，false为失败
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean update(SysDictDataDto dto) {
        checkUnique(dto);
        exists(dto.getId());
        SysDictData sysDictData = getById(dto.getId());
        BeanUtil.copyProperties(dto, sysDictData);
        return updateById(sysDictData);
    }

    /**
     * 删除
     *
     * @param id id
     * @return 删除结果，true为成功，false为失败
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean remove(Id<Long> id) {
        if (ObjectUtils.isNotEmpty(id.getId())) {
            exists(id.getId());
            return removeById(id.getId());
        } else if (CollectionUtils.isNotEmpty(id.getIds())) {
            return removeByIds(id.getIds());
        } else {
            throw new BaseException(ExceptionEnum.REQUEST_PARAM_ERROR);
        }
    }

    /**
     * 导入
     *
     * @param file 要导入的excel文件。
     * @return 是否导入成功
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public String importData(MultipartFile file) {
        int successCount = 0;
        int failCount = 0;
        for (SysDictDataDto dto : ExcelUtil.read(file, SysDictDataDto.class)) {
            if (add(dto)) {
                successCount++;
            } else {
                failCount++;
            }
        }
        log.info("导入成功{}条，失败{}条", successCount, failCount);
        return StringUtil.format("导入成功{}条，失败{}条", successCount, failCount);
    }

    /**
     * 导出
     */
    @Override
    public void export(HttpServletResponse response) {
        ExcelUtil.export(list(), "字典数据", SysDictData.class, response);
    }

    @Override
    public List<SelectOption<String>> toSelectOption(String typeCode) {
        return list(new LambdaQueryWrapper<SysDictData>().eq(SysDictData::getTypeCode, typeCode))
                .stream()
                .map(SysDictData::toSelectOption)
                .toList();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeByTypeCode(String typeCode) {
        return removeByIds(list(new LambdaQueryWrapper<SysDictData>().eq(SysDictData::getTypeCode, typeCode)));
    }
}
