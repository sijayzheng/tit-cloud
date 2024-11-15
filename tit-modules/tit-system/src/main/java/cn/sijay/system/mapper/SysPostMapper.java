package cn.sijay.system.mapper;

import cn.sijay.system.entity.SysPost;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * <strong>SysPostMapper</strong>
 * <p>
 * 岗位信息持久化层
 * </p>
 *
 * @author sijay
 * @since 2024-04-25
 */
@Mapper
public interface SysPostMapper extends BaseMapper<SysPost> {
}
