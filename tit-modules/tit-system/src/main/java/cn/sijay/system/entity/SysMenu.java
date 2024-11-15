package cn.sijay.system.entity;

import cn.sijay.common.core.constant.Constants;
import cn.sijay.common.core.utils.BeanUtil;
import cn.sijay.common.core.utils.StringUtil;
import cn.sijay.common.core.utils.ValidateUtil;
import cn.sijay.common.mybatis.annotation.QueryColumn;
import cn.sijay.common.mybatis.dictionary.MenuType;
import cn.sijay.common.mybatis.dictionary.QueryType;
import cn.sijay.common.web.base.BaseEntityWithVersion;
import cn.sijay.system.dto.SysMenuDto;
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
 * <strong>SysMenu</strong>
 * <p>
 * 菜单信息 实体类
 * </p>
 *
 * @author sijay
 * @since 2024-04-25
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_menu")
@Schema(name = "SysMenu", title = "菜单信息", description = "菜单信息")
public class SysMenu extends BaseEntityWithVersion {

    /**
     * id
     */
    @Schema(title = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 上级
     */
    @Schema(title = "上级")
    @QueryColumn(QueryType.EQUAL)
    @TableField("parent_id")
    private Long parentId;

    /**
     * 菜单名称
     */
    @Schema(title = "菜单名称")
    @QueryColumn(QueryType.LIKE)
    @TableField("name")
    private String name;

    /**
     * 菜单类型
     */
    @Schema(title = "菜单类型")
    @QueryColumn(QueryType.LIKE)
    @TableField("type")
    private MenuType type;

    /**
     * 路径
     */
    @Schema(title = "路径")
    @QueryColumn(QueryType.LIKE)
    @TableField("path")
    private String path;

    /**
     * 组件路径
     */
    @Schema(title = "组件路径")
    @QueryColumn(QueryType.LIKE)
    @TableField("component")
    private String component;

    /**
     * 路由参数
     */
    @Schema(title = "路由参数")
    @QueryColumn(QueryType.LIKE)
    @TableField("query_param")
    private String queryParam;

    /**
     * 权限标识
     */
    @Schema(title = "权限标识")
    @QueryColumn(QueryType.LIKE)
    @TableField("perms")
    private String perms;

    /**
     * 图标
     */
    @Schema(title = "图标")
    @QueryColumn(QueryType.LIKE)
    @TableField("icon")
    private String icon;

    /**
     * 排序
     */
    @Schema(title = "排序")
    @QueryColumn(QueryType.EQUAL)
    @TableField("sort")
    private Integer sort;

    /**
     * 是否为外链
     */
    @Schema(title = "是否为外链")
    @QueryColumn(QueryType.EQUAL)
    @TableField("link")
    private Boolean link;

    /**
     * 是否缓存
     */
    @Schema(title = "是否缓存")
    @QueryColumn(QueryType.EQUAL)
    @TableField("cache")
    private Boolean cache;

    /**
     * 显示状态
     */
    @Schema(title = "显示状态")
    @QueryColumn(QueryType.EQUAL)
    @TableField("visible")
    private Boolean visible;

    /**
     * 是否启用
     */
    @Schema(title = "是否启用")
    @QueryColumn(QueryType.EQUAL)
    @TableField("enabled")
    private Boolean enabled;

    /**
     * 是否删除
     */
    @Schema(title = "是否删除")
    @TableField("deleted")
    private Boolean deleted;

    @TableField(exist = false)
    private List<SysMenu> children;

    /**
     * 内链域名特殊字符替换
     */
    public static String innerLinkReplaceEach(String path) {
        return StringUtil.replaceEach(path, new String[]{Constants.HTTP, Constants.HTTPS, Constants.WWW, ".", ":"}, new String[]{"", "", "", "/", "/"});
    }

    public SysMenuDto toSysMenuDto() {
        return BeanUtil.copyProperties(this, SysMenuDto.class);
    }

    /**
     * 获取路由名称
     */
    public String getRouteName() {
        String routerName = StringUtil.capitalize(path);
        // 非外链并且是一级目录（类型为目录）
        if (isMenuFrame()) {
            routerName = StringUtil.EMPTY;
        }
        return routerName;
    }

    /**
     * 获取路由地址
     */
    public String getRouterPath() {
        String routerPath = this.path;
        // 内链打开外网方式
        if (getParentId() != 0L && isInnerLink()) {
            routerPath = innerLinkReplaceEach(routerPath);
        }
        // 非外链并且是一级目录（类型为目录）
        if (0L == getParentId() && MenuType.DIRECTORY.equals(getType()) && getLink()) {
            routerPath = "/" + this.path;
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isMenuFrame()) {
            routerPath = "/";
        }
        return routerPath;
    }

    /**
     * 获取组件信息
     */
    public String getComponentInfo() {
        String component = "Layout";
        if (StringUtil.isNotEmpty(this.component) && !isMenuFrame()) {
            component = this.component;
        } else if (StringUtil.isEmpty(this.component) && getParentId() != 0L && isInnerLink()) {
            component = "InnerLink";
        } else if (StringUtil.isEmpty(this.component) && isParentView()) {
            component = "ParentView";
        }
        return component;
    }

    /**
     * 是否为菜单内部跳转
     */
    public boolean isMenuFrame() {
        return getParentId() == 0L && MenuType.MENU.equals(getType()) && getLink();
    }

    /**
     * 是否为内链组件
     */
    public boolean isInnerLink() {
        return getLink() && ValidateUtil.isHttp(path);
    }

    /**
     * 是否为parent_view组件
     */
    public boolean isParentView() {
        return getParentId() != 0L && MenuType.DIRECTORY.equals(getType());
    }
}
