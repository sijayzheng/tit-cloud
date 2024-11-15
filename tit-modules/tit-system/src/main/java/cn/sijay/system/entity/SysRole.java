package cn.sijay.system.entity;

import cn.sijay.common.core.utils.BeanUtil;
import cn.sijay.common.mybatis.annotation.QueryColumn;
import cn.sijay.common.mybatis.dictionary.QueryType;
import cn.sijay.common.web.base.BaseEntityWithVersion;
import cn.sijay.system.dto.SysRoleDto;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <strong>SysRole</strong>
 * <p>
 * 角色信息 实体类
 * </p>
 *
 * @author sijay
 * @since 2024-04-25
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_role")
@Schema(name = "SysRole", title = "角色信息", description = "角色信息")
public class SysRole extends BaseEntityWithVersion {

    /**
     * id
     */
    @Schema(title = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色名称
     */
    @Schema(title = "角色名称")
    @QueryColumn(QueryType.LIKE)
    @TableField("name")
    private String name;

    /**
     * 角色编码
     */
    @Schema(title = "角色编码")
    @QueryColumn(QueryType.LIKE)
    @TableField("code")
    private String code;

    /**
     * 菜单
     */
    @Schema(title = "菜单")
    @QueryColumn(QueryType.LIKE)
    @TableField("menu_ids")
    private List<Long> menuIds;

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

    public SysRoleDto toSysRoleDto() {
        return BeanUtil.copyProperties(this, SysRoleDto.class);
    }
}
