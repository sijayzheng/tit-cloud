package cn.sijay.system.entity;

import cn.sijay.common.core.utils.BeanUtil;
import cn.sijay.common.mybatis.annotation.QueryColumn;
import cn.sijay.common.mybatis.dictionary.QueryType;
import cn.sijay.common.web.base.BaseEntityWithVersion;
import cn.sijay.system.dto.SysNoticeDto;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <strong>SysNotice</strong>
 * <p>
 * 通知公告 实体类
 * </p>
 *
 * @author sijay
 * @since 2024-04-25
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_notice")
@Schema(name = "SysNotice", title = "通知公告", description = "通知公告")
public class SysNotice extends BaseEntityWithVersion {

    /**
     * id
     */
    @Schema(title = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     */
    @Schema(title = "标题")
    @QueryColumn(QueryType.LIKE)
    @TableField("title")
    private String title;

    /**
     * 内容
     */
    @Schema(title = "内容")
    @QueryColumn(QueryType.LIKE)
    @TableField("content")
    private String content;

    /**
     * 类型
     */
    @Schema(title = "类型")
    @QueryColumn(QueryType.LIKE)
    @TableField("type")
    private String type;

    /**
     * 状态
     */
    @Schema(title = "状态")
    @QueryColumn(QueryType.LIKE)
    @TableField("status")
    private String status;

    /**
     * 是否删除
     */
    @Schema(title = "是否删除")
    @TableField("deleted")
    private Boolean deleted;

    public SysNoticeDto toSysNoticeDto() {
        return BeanUtil.copyProperties(this, SysNoticeDto.class);
    }
}
