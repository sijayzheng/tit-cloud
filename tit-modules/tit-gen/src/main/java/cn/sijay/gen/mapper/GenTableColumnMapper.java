package cn.sijay.gen.mapper;

import cn.sijay.gen.entity.GenTableColumn;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <strong>GenTableColumnMapper</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-04-12
 */
@Mapper
public interface GenTableColumnMapper extends BaseMapper<GenTableColumn> {
    List<GenTableColumn> listTableColumns(@Param("tableName") String tableName);
}
