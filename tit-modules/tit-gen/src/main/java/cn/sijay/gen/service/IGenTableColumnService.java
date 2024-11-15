package cn.sijay.gen.service;

import cn.sijay.common.core.entity.Id;
import cn.sijay.gen.entity.GenTableColumn;
import com.baomidou.mybatisplus.extension.service.IService;

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
public interface IGenTableColumnService extends IService<GenTableColumn> {
    List<GenTableColumn> listByTableId(Long id);

    boolean removeBatchByTableId(Id<Long> id);

    List<GenTableColumn> listByTableName(String tableName);
}
