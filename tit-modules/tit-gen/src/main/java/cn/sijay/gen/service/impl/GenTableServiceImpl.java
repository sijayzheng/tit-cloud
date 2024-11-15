package cn.sijay.gen.service.impl;

import cn.sijay.common.core.entity.Id;
import cn.sijay.common.core.enums.ExceptionEnum;
import cn.sijay.common.core.exception.BaseException;
import cn.sijay.common.core.utils.BeanUtil;
import cn.sijay.common.core.utils.StringUtil;
import cn.sijay.common.mybatis.dictionary.SuperClassType;
import cn.sijay.common.mybatis.entity.PageQuery;
import cn.sijay.gen.entity.GenTable;
import cn.sijay.gen.mapper.GenTableMapper;
import cn.sijay.gen.service.IGenTableColumnService;
import cn.sijay.gen.service.IGenTableService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <strong>GenTableServiceImpl</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-04-12
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class GenTableServiceImpl extends ServiceImpl<GenTableMapper, GenTable> implements IGenTableService {

    private final IGenTableColumnService genTableColumnService;

    boolean exists(Long id) {
        return exists(new LambdaQueryWrapper<GenTable>().eq(GenTable::getId, id));
    }

    LambdaQueryWrapper<GenTable> buildQueryWrapper(GenTable entity) {
        LambdaQueryWrapper<GenTable> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtil.isNotBlank(entity.getTableName()), GenTable::getTableName, entity.getTableName());
        wrapper.like(StringUtil.isNotBlank(entity.getTableComment()), GenTable::getTableComment, entity.getTableComment());
        return wrapper;
    }

    @Override
    public List<GenTable> listTable() {
        List<String> list = list().stream().map(GenTable::getTableName).toList();
        return baseMapper.listTable()
                         .stream()
                         .filter(item -> !list.contains(item.getTableName()))
                         .peek(item -> {
                             item.setClassName(StringUtil.toUpperCamelCase(item.getTableName()));
                             item.setModuleName(item.getTableName().split("_")[0]);
                             item.setBusinessName(StringUtil.toLowerCamelCase(item.getClassName()));
                             item.setFunctionName(item.getTableComment().replaceAll("表$", ""));
                             item.setSuperClass(SuperClassType.NONE);
                         })
                         .collect(Collectors.toList());
    }

    @Override
    public IPage<GenTable> page(GenTable entity, PageQuery pageQuery) {
        return page(pageQuery.build(), buildQueryWrapper(entity));
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean remove(Id<Long> id) {
        if (ObjectUtils.isNotEmpty(id.getId())) {
            if (exists(id.getId())) {
                return genTableColumnService.removeBatchByTableId(id) && removeById(id.getId());
            } else {
                throw new BaseException(ExceptionEnum.DATA_NOT_FOUND, id.getId());
            }
        } else if (CollectionUtils.isNotEmpty(id.getIds())) {
            List<Long> tableIds = id.getIds().stream().filter(item -> !exists(item)).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(tableIds)) {
                return genTableColumnService.removeBatchByTableId(id) && removeByIds(id.getIds());
            } else {
                throw new BaseException(ExceptionEnum.DATA_NOT_FOUND, tableIds);
            }
        } else {
            throw new BaseException(ExceptionEnum.REQUEST_PARAM_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean update(GenTable entity) {
        if (exists(entity.getId())) {
            GenTable genTable = getById(entity.getId());
            BeanUtil.copyProperties(entity, genTable);
            return updateById(genTable);
        }
        return false;
    }
}
