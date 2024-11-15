package cn.sijay.system.mapper;

import cn.sijay.system.entity.SysNotice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * <strong>SysNoticeMapper</strong>
 * <p>
 * 通知公告持久化层
 * </p>
 *
 * @author sijay
 * @since 2024-04-25
 */
@Mapper
public interface SysNoticeMapper extends BaseMapper<SysNotice> {
}
