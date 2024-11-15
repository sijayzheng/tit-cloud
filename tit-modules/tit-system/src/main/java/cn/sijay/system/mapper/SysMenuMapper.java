package cn.sijay.system.mapper;

import cn.sijay.system.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * <strong>SysMenuMapper</strong>
 * <p>
 * 菜单信息持久化层
 * </p>
 *
 * @author sijay
 * @since 2024-04-25
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
}
