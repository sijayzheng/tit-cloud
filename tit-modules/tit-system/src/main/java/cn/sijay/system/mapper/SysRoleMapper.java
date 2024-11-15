package cn.sijay.system.mapper;

import cn.sijay.system.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * <strong>SysRoleMapper</strong>
 * <p>
 * 角色信息持久化层
 * </p>
 *
 * @author sijay
 * @since 2024-04-25
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
}
