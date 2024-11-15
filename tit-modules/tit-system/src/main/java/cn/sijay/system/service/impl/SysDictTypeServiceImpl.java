package cn.sijay.system.service.impl;

import cn.sijay.common.core.annotation.Dictionary;
import cn.sijay.common.core.entity.Id;
import cn.sijay.common.core.entity.SelectOption;
import cn.sijay.common.core.enums.ExceptionEnum;
import cn.sijay.common.core.exception.BaseException;
import cn.sijay.common.core.utils.BeanUtil;
import cn.sijay.common.core.utils.StringUtil;
import cn.sijay.common.mybatis.entity.PageQuery;
import cn.sijay.common.web.utils.ExcelUtil;
import cn.sijay.system.dto.SysDictTypeDto;
import cn.sijay.system.entity.SysDictData;
import cn.sijay.system.entity.SysDictType;
import cn.sijay.system.mapper.SysDictTypeMapper;
import cn.sijay.system.service.ISysDictDataService;
import cn.sijay.system.service.ISysDictTypeService;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * <strong>SysDictTypeService</strong>
 * <p>
 * 字典类型服务层实现类
 * </p>
 *
 * @author sijay
 * @since 2024-04-28
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SysDictTypeServiceImpl extends ServiceImpl<SysDictTypeMapper, SysDictType> implements ISysDictTypeService {
    private final ISysDictDataService sysDictDataService;

    /**
     * 分页查询
     *
     * @param dto       数据传输对象
     * @param pageQuery 分页查询条件
     * @return 分页查询结果
     */
    @Override
    public IPage<SysDictTypeDto> page(SysDictTypeDto dto, PageQuery pageQuery) {
        return page(pageQuery.build(), buildQueryWrapper(dto, SysDictTypeDto.class)).convert(SysDictType::toSysDictTypeDto);
    }

    /**
     * 新增
     *
     * @param dto 数据传输对象
     * @return 新增结果，true为成功，false为失败
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean add(SysDictTypeDto dto) {
        checkUnique(dto);
        return save(dto.toSysDictType());
    }

    /**
     * 更新
     *
     * @param dto 数据传输对象
     * @return 更新结果，true为成功，false为失败
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean update(SysDictTypeDto dto) {
        checkUnique(dto);
        exists(dto.getId());
        SysDictType sysDictType = getById(dto.getId());
        BeanUtil.copyProperties(dto, sysDictType);
        return updateById(sysDictType);
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
        for (SysDictTypeDto dto : ExcelUtil.read(file, SysDictTypeDto.class)) {
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
        ExcelUtil.export(list(), "字典类型", SysDictType.class, response);
    }

    @Override
    public void syncDict() {
        try {
            for (Resource resource : new PathMatchingResourcePatternResolver().getResources("classpath*:cn/sijay/common/mybatis/dictionary/**/*.class")) {
                Class<?> clazz = ClassLoader.getSystemClassLoader()
                                            .loadClass(new CachingMetadataReaderFactory().getMetadataReader(resource).getClassMetadata()
                                                                                         .getClassName());
                if (clazz.isAnnotationPresent(Dictionary.class)) {
                    String typeCode = clazz.getSimpleName();
                    if (!exists(new QueryWrapper<SysDictType>().eq("code", typeCode))) {
                        System.out.println(typeCode);
                        SysDictType dictType = new SysDictType().setCode(typeCode).setName(typeCode).setInternal(true);
                        List<SysDictData> list = new ArrayList<>();
                        for (Enum en : (Enum[]) clazz.getEnumConstants()) {
                            SysDictData sysDictData = new SysDictData();
                            boolean add = false;
                            for (Field field : clazz.getDeclaredFields()) {
                                field.setAccessible(true);
                                if (field.isAnnotationPresent(EnumValue.class)) {
                                    sysDictData.setValue((String) field.get(en));
                                    sysDictData.setSort(en.ordinal());
                                } else if (field.isAnnotationPresent(JsonValue.class)) {
                                    sysDictData.setLabel((String) field.get(en));
                                    add = exists(new QueryWrapper<SysDictType>().eq("label", field.get(en)));
                                }
                            }
                            if (add) {
                                list.add(sysDictData);
                            }
                        }
                        if (save(dictType) && sysDictDataService.removeByTypeCode(typeCode)) {
                            for (SysDictData dictData : list) {
                                dictData.setTypeCode(typeCode);
                                sysDictDataService.save(dictData);
                            }
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | IOException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SelectOption<String>> toOptions() {
        return list().stream()
                     .map(item -> new SelectOption<>(item.getName(), item.getCode()))
                     .toList();
    }
}
