package cn.sijay.gen.service.impl;

import cn.sijay.common.core.entity.Id;
import cn.sijay.common.core.enums.ExceptionEnum;
import cn.sijay.common.core.exception.BaseException;
import cn.sijay.gen.entity.GenTableColumn;
import cn.sijay.gen.mapper.GenTableColumnMapper;
import cn.sijay.gen.service.IGenTableColumnService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <strong>IGenTableColumnService</strong>
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
public class GenTableColumnServiceImpl extends ServiceImpl<GenTableColumnMapper, GenTableColumn> implements IGenTableColumnService {

    @Override
    public List<GenTableColumn> listByTableId(Long tableId) {
        return list(new LambdaQueryWrapper<GenTableColumn>().eq(GenTableColumn::getTableId, tableId)
                                                            .orderByAsc(GenTableColumn::getSort));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeBatchByTableId(Id<Long> id) {
        if (ObjectUtils.isNotEmpty(id.getId())) {
            return removeBatchByIds(listByTableId(id.getId()));
        } else if (CollectionUtils.isNotEmpty(id.getIds())) {
            List<Long> list = list(new LambdaQueryWrapper<GenTableColumn>().in(GenTableColumn::getTableId, id.getIds())).stream()
                                                                                                                        .map(GenTableColumn::getId)
                                                                                                                        .toList();
            return removeByIds(list);
        } else {
            throw new BaseException(ExceptionEnum.REQUEST_PARAM_ERROR);
        }
    }

    @Override
    public List<GenTableColumn> listByTableName(String tableName) {
        return baseMapper.listTableColumns(tableName);
    }

}
