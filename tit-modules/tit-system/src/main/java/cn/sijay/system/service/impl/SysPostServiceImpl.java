package cn.sijay.system.service.impl;

import cn.sijay.common.core.entity.Id;
import cn.sijay.common.core.enums.ExceptionEnum;
import cn.sijay.common.core.exception.BaseException;
import cn.sijay.common.core.utils.BeanUtil;
import cn.sijay.common.core.utils.StringUtil;
import cn.sijay.common.mybatis.entity.PageQuery;
import cn.sijay.common.web.utils.ExcelUtil;
import cn.sijay.system.dto.SysPostDto;
import cn.sijay.system.entity.SysPost;
import cn.sijay.system.mapper.SysPostMapper;
import cn.sijay.system.service.ISysPostService;
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

/**
 * <strong>SysPostService</strong>
 * <p>
 * 岗位信息服务层实现类
 * </p>
 *
 * @author sijay
 * @since 2024-04-28
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SysPostServiceImpl extends ServiceImpl<SysPostMapper, SysPost> implements ISysPostService {

    /**
     * 分页查询
     *
     * @param dto       数据传输对象
     * @param pageQuery 分页查询条件
     * @return 分页查询结果
     */
    @Override
    public IPage<SysPostDto> page(SysPostDto dto, PageQuery pageQuery) {
        return page(pageQuery.build(), buildQueryWrapper(dto, SysPostDto.class)).convert(SysPost::toSysPostDto);
    }

    /**
     * 新增
     *
     * @param dto 数据传输对象
     * @return 新增结果，true为成功，false为失败
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean add(SysPostDto dto) {
        checkUnique(dto);
        return save(dto.toSysPost());
    }

    /**
     * 更新
     *
     * @param dto 数据传输对象
     * @return 更新结果，true为成功，false为失败
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean update(SysPostDto dto) {
        checkUnique(dto);
        exists(dto.getId());
        SysPost sysPost = getById(dto.getId());
        BeanUtil.copyProperties(dto, sysPost);
        return updateById(sysPost);
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
        for (SysPostDto dto : ExcelUtil.read(file, SysPostDto.class)) {
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
        ExcelUtil.export(list(), "岗位信息", SysPost.class, response);
    }
}
