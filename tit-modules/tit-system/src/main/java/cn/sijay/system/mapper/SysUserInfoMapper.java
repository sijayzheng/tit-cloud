package cn.sijay.system.mapper;

import cn.sijay.system.entity.SysUserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * <strong>SysUserInfoMapper</strong>
 * <p>
 * 用户信息持久化层
 * </p>
 *
 * @author sijay
 * @since 2024-04-25
 */
@Mapper
public interface SysUserInfoMapper extends BaseMapper<SysUserInfo> {
}
