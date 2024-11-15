package cn.sijay.system.dto;

import cn.sijay.common.core.utils.BeanUtil;
import cn.sijay.common.mybatis.annotation.QueryColumn;
import cn.sijay.common.mybatis.dictionary.QueryType;
import cn.sijay.system.entity.SysRole;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
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
@Accessors(chain = true)
@Schema(name = "SysRole", title = "角色信息", description = "角色信息")
public class SysRoleDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Schema(title = "id")
    private Long id;

    /**
     * 角色名称
     */
    @Schema(title = "角色名称")
    @ExcelProperty(value = "角色名称", order = 0)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 50, message = "字段[角色名称]最大长度为{max}")
    private String name;

    /**
     * 角色编码
     */
    @Schema(title = "角色编码")
    @ExcelProperty(value = "角色编码", order = 1)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 50, message = "字段[角色编码]最大长度为{max}")
    private String code;

    /**
     * 菜单
     */
    @Schema(title = "菜单")
    @ExcelProperty(value = "菜单", order = 2)
    @QueryColumn(QueryType.LIKE)
    private List<Long> menuIds;

    /**
     * 排序
     */
    @Schema(title = "排序")
    @ExcelProperty(value = "排序", order = 3)
    @QueryColumn(QueryType.EQUAL)
    private Integer sort;

    /**
     * 是否删除
     */
    @Schema(title = "是否删除")
    private Boolean deleted;

    public SysRole toSysRole() {
        return BeanUtil.copyProperties(this, SysRole.class);
    }
}
