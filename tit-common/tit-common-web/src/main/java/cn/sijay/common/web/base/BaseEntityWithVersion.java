package cn.sijay.common.web.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * <strong>BaseEntityWithVersion</strong>
 * <p>
 * BaseEntityWithVersion
 * </p>
 *
 * @author Sijay
 * @since 2024-04-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BaseEntityWithVersion extends BaseEntity {

    /**
     * 版本号
     */
    @Schema(title = "版本号")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Version
    @TableField(value = "version", fill = FieldFill.INSERT_UPDATE)
    private long version;

}
