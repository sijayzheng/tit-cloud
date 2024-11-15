package cn.sijay.system.mapper;

import cn.sijay.system.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * <strong>SysUserMapper</strong>
 * <p>
 * 登录用户持久化层
 * </p>
 *
 * @author sijay
 * @since 2024-04-25
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
}
