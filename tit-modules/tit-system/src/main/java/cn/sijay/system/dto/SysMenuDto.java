package cn.sijay.system.dto;

import cn.sijay.common.core.utils.BeanUtil;
import cn.sijay.common.mybatis.annotation.QueryColumn;
import cn.sijay.common.mybatis.dictionary.QueryType;
import cn.sijay.system.entity.SysMenu;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * <strong>SysMenu</strong>
 * <p>
 * 菜单信息 实体类
 * </p>
 *
 * @author sijay
 * @since 2024-04-25
 */
@Data
@Accessors(chain = true)
@Schema(name = "SysMenu", title = "菜单信息", description = "菜单信息")
public class SysMenuDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Schema(title = "id")
    private Long id;

    /**
     * 上级
     */
    @Schema(title = "上级")
    @ExcelProperty(value = "上级", order = 0)
    @QueryColumn(QueryType.EQUAL)
    private Long parentId;

    /**
     * 菜单名称
     */
    @Schema(title = "菜单名称")
    @ExcelProperty(value = "菜单名称", order = 1)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 50, message = "字段[菜单名称]最大长度为{max}")
    private String name;

    /**
     * 菜单类型
     */
    @Schema(title = "菜单类型")
    @ExcelProperty(value = "菜单类型", order = 2)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 1, message = "字段[菜单类型]最大长度为{max}")
    private String type;

    /**
     * 路径
     */
    @Schema(title = "路径")
    @ExcelProperty(value = "路径", order = 3)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 200, message = "字段[路径]最大长度为{max}")
    private String path;

    /**
     * 组件路径
     */
    @Schema(title = "组件路径")
    @ExcelProperty(value = "组件路径", order = 4)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 255, message = "字段[组件路径]最大长度为{max}")
    private String component;

    /**
     * 路由参数
     */
    @Schema(title = "路由参数")
    @ExcelProperty(value = "路由参数", order = 5)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 255, message = "字段[路由参数]最大长度为{max}")
    private String queryParam;

    /**
     * 权限标识
     */
    @Schema(title = "权限标识")
    @ExcelProperty(value = "权限标识", order = 6)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 100, message = "字段[权限标识]最大长度为{max}")
    private String perms;

    /**
     * 图标
     */
    @Schema(title = "图标")
    @ExcelProperty(value = "图标", order = 7)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 100, message = "字段[图标]最大长度为{max}")
    private String icon;

    /**
     * 排序
     */
    @Schema(title = "排序")
    @ExcelProperty(value = "排序", order = 8)
    @QueryColumn(QueryType.EQUAL)
    private Integer sort;

    /**
     * 是否为外链
     */
    @Schema(title = "是否为外链")
    @ExcelProperty(value = "是否为外链", order = 9)
    @QueryColumn(QueryType.EQUAL)
    private Boolean link;

    /**
     * 是否缓存
     */
    @Schema(title = "是否缓存")
    @ExcelProperty(value = "是否缓存", order = 10)
    @QueryColumn(QueryType.EQUAL)
    private Boolean cache;

    /**
     * 显示状态
     */
    @Schema(title = "显示状态")
    @ExcelProperty(value = "显示状态", order = 11)
    @QueryColumn(QueryType.EQUAL)
    private Boolean visible;

    /**
     * 是否启用
     */
    @Schema(title = "是否启用")
    @ExcelProperty(value = "是否启用", order = 12)
    @QueryColumn(QueryType.EQUAL)
    private Boolean enabled;

    /**
     * 是否删除
     */
    @Schema(title = "是否删除")
    private Boolean deleted;

    public SysMenu toSysMenu() {
        return BeanUtil.copyProperties(this, SysMenu.class);
    }
}
