package cn.sijay.common.web.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <strong>BaseEntity</strong>
 * <p>
 * BaseEntity
 * </p>
 *
 * @author Sijay
 * @since 2024-04-04
 */
@Data
public class BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    
    /**
     * 创建部门
     */
    @Schema(title = "'创建部门'", accessMode = Schema.AccessMode.READ_ONLY)
    @TableField(value = "create_dept", fill = FieldFill.INSERT)
    private Long createDept;

    /**
     * 创建者
     */
    @Schema(title = "创建者", accessMode = Schema.AccessMode.READ_ONLY)
    @TableField(value = "creator", fill = FieldFill.INSERT)
    private Long creator;

    /**
     * 创建时间
     */
    @Schema(title = "创建时间", accessMode = Schema.AccessMode.READ_ONLY)
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    @Schema(title = "更新者", accessMode = Schema.AccessMode.READ_ONLY)
    @TableField(value = "updater", fill = FieldFill.UPDATE)
    private Long updater;

    /**
     * 更新时间
     */
    @Schema(title = "更新时间", accessMode = Schema.AccessMode.READ_ONLY)
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

}
