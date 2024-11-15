package cn.sijay.system.entity;

import cn.sijay.common.core.utils.BeanUtil;
import cn.sijay.common.mybatis.annotation.QueryColumn;
import cn.sijay.common.mybatis.dictionary.QueryType;
import cn.sijay.common.web.base.BaseEntityWithVersion;
import cn.sijay.system.dto.SysConfigDto;
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
 * <strong>SysConfig</strong>
 * <p>
 * 系统配置 实体类
 * </p>
 *
 * @author sijay
 * @since 2024-04-25
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_config")
@Schema(name = "SysConfig", title = "系统配置", description = "系统配置")
public class SysConfig extends BaseEntityWithVersion {

    /**
     * id
     */
    @Schema(title = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 配置名称
     */
    @Schema(title = "配置名称")
    @QueryColumn(QueryType.LIKE)
    @TableField("name")
    private String name;

    /**
     * 配置编码
     */
    @Schema(title = "配置编码")
    @QueryColumn(QueryType.LIKE)
    @TableField("code")
    private String code;

    /**
     * 配置值
     */
    @Schema(title = "配置值")
    @QueryColumn(QueryType.LIKE)
    @TableField("value")
    private String value;

    /**
     * 排序
     */
    @Schema(title = "排序")
    @QueryColumn(QueryType.EQUAL)
    @TableField("sort")
    private Integer sort;

    /**
     * 是否删除
     */
    @Schema(title = "是否删除")
    @TableField("deleted")
    private Boolean deleted;

    public SysConfigDto toSysConfigDto() {
        return BeanUtil.copyProperties(this, SysConfigDto.class);
    }
}
