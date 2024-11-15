package cn.sijay.system.mapper;

import cn.sijay.system.entity.SysDept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * <strong>SysDeptMapper</strong>
 * <p>
 * 部门信息持久化层
 * </p>
 *
 * @author sijay
 * @since 2024-04-25
 */
@Mapper
public interface SysDeptMapper extends BaseMapper<SysDept> {
}
