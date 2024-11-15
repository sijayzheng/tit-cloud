package cn.sijay.gen.mapper;

import cn.sijay.gen.entity.GenTable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <strong>GenTableMapper</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-04-12
 */
@Mapper
public interface GenTableMapper extends BaseMapper<GenTable> {
    List<GenTable> listTable();
}
