package cn.sijay.system.mapper;

import cn.sijay.system.entity.SysModule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * <strong>SysModuleMapper</strong>
 * <p>
 * 系统模块持久化层
 * </p>
 *
 * @author sijay
 * @since 2024-04-28
 */
@Mapper
public interface SysModuleMapper extends BaseMapper<SysModule> {
}
