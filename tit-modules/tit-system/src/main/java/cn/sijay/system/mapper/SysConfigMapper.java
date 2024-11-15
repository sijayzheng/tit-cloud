package cn.sijay.system.mapper;

import cn.sijay.system.entity.SysConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * <strong>SysConfigMapper</strong>
 * <p>
 * 系统配置持久化层
 * </p>
 *
 * @author sijay
 * @since 2024-04-25
 */
@Mapper
public interface SysConfigMapper extends BaseMapper<SysConfig> {
}
