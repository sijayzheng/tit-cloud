package cn.sijay.system.entity;

import cn.sijay.common.core.utils.BeanUtil;
import cn.sijay.common.mybatis.annotation.QueryColumn;
import cn.sijay.common.mybatis.dictionary.QueryType;
import cn.sijay.common.web.base.BaseEntityWithVersion;
import cn.sijay.system.dto.SysUserDto;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <strong>SysUser</strong>
 * <p>
 * 登录用户 实体类
 * </p>
 *
 * @author sijay
 * @since 2024-04-25
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user")
@Schema(name = "SysUser", title = "登录用户", description = "登录用户")
public class SysUser extends BaseEntityWithVersion {

    /**
     * id
     */
    @Schema(title = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @Schema(title = "用户名")
    @QueryColumn(QueryType.LIKE)
    @TableField("username")
    private String username;

    /**
     * 手机号
     */
    @Schema(title = "手机号")
    @QueryColumn(QueryType.LIKE)
    @TableField("phone")
    private String phone;

    /**
     * 邮箱
     */
    @Schema(title = "邮箱")
    @QueryColumn(QueryType.LIKE)
    @TableField("email")
    private String email;

    /**
     * 密码
     */
    @Schema(title = "密码")
    @QueryColumn(QueryType.LIKE)
    @TableField("password")
    private String password;

    /**
     * 部门
     */
    @Schema(title = "部门")
    @QueryColumn(QueryType.EQUAL)
    @TableField("dept_id")
    private Long deptId;

    /**
     * 角色
     */
    @Schema(title = "角色")
    @QueryColumn(QueryType.LIKE)
    @TableField("role_ids")
    private List<Long> roleIds;

    /**
     * 岗位
     */
    @Schema(title = "岗位")
    @QueryColumn(QueryType.LIKE)
    @TableField("post_ids")
    private List<Long> postIds;

    /**
     * 是否启用
     */
    @Schema(title = "是否启用")
    @QueryColumn(QueryType.EQUAL)
    @TableField("enable")
    private Boolean enable;

    /**
     * 排序
     */
    @Schema(title = "排序")
    @QueryColumn(QueryType.EQUAL)
    @TableField("sort")
    private Integer sort;

    /**
     * 最后登录时间
     */
    @Schema(title = "最后登录时间")
    @QueryColumn(QueryType.BETWEEN)
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;

    /**
     * 是否删除
     */
    @Schema(title = "是否删除")
    @TableField("deleted")
    private Boolean deleted;

    /**
     * 最后登录时间开始
     */
    @TableField(exist = false)
    private LocalDateTime lastLoginTimeStart;

    /**
     * 最后登录时间结束
     */
    @TableField(exist = false)
    private LocalDateTime lastLoginTimeEnd;

    public SysUserDto toSysUserDto() {
        return BeanUtil.copyProperties(this, SysUserDto.class);
    }
}
