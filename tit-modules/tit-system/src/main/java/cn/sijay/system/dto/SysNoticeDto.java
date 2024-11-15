package cn.sijay.system.dto;

import cn.sijay.common.core.utils.BeanUtil;
import cn.sijay.common.mybatis.annotation.QueryColumn;
import cn.sijay.common.mybatis.dictionary.QueryType;
import cn.sijay.system.entity.SysNotice;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

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
@Accessors(chain = true)
@Schema(name = "SysNotice", title = "通知公告", description = "通知公告")
public class SysNoticeDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Schema(title = "id")
    private Long id;

    /**
     * 标题
     */
    @Schema(title = "标题")
    @ExcelProperty(value = "标题", order = 0)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 50, message = "字段[标题]最大长度为{max}")
    private String title;

    /**
     * 内容
     */
    @Schema(title = "内容")
    @ExcelProperty(value = "内容", order = 1)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 500, message = "字段[内容]最大长度为{max}")
    private String content;

    /**
     * 类型
     */
    @Schema(title = "类型")
    @ExcelProperty(value = "类型", order = 2)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 10, message = "字段[类型]最大长度为{max}")
    private String type;

    /**
     * 状态
     */
    @Schema(title = "状态")
    @ExcelProperty(value = "状态", order = 3)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 10, message = "字段[状态]最大长度为{max}")
    private String status;

    /**
     * 是否删除
     */
    @Schema(title = "是否删除")
    private Boolean deleted;

    public SysNotice toSysNotice() {
        return BeanUtil.copyProperties(this, SysNotice.class);
    }
}
