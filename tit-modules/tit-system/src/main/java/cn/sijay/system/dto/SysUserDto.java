package cn.sijay.system.dto;

import cn.sijay.common.core.utils.BeanUtil;
import cn.sijay.common.mybatis.annotation.QueryColumn;
import cn.sijay.common.mybatis.dictionary.QueryType;
import cn.sijay.system.entity.SysUser;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
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
@Accessors(chain = true)
@Schema(name = "SysUser", title = "登录用户", description = "登录用户")
public class SysUserDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Schema(title = "id")
    private Long id;

    /**
     * 用户名
     */
    @Schema(title = "用户名")
    @ExcelProperty(value = "用户名", order = 0)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 50, message = "字段[用户名]最大长度为{max}")
    private String username;

    /**
     * 手机号
     */
    @Schema(title = "手机号")
    @ExcelProperty(value = "手机号", order = 1)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 11, message = "字段[手机号]最大长度为{max}")
    private String phone;

    /**
     * 邮箱
     */
    @Schema(title = "邮箱")
    @ExcelProperty(value = "邮箱", order = 2)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 100, message = "字段[邮箱]最大长度为{max}")
    private String email;

    /**
     * 密码
     */
    @Schema(title = "密码")
    @ExcelProperty(value = "密码", order = 3)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 128, message = "字段[密码]最大长度为{max}")
    private String password;

    /**
     * 部门
     */
    @Schema(title = "部门")
    @ExcelProperty(value = "部门", order = 4)
    @QueryColumn(QueryType.EQUAL)
    private Long deptId;

    /**
     * 角色
     */
    @Schema(title = "角色")
    @ExcelProperty(value = "角色", order = 5)
    @QueryColumn(QueryType.LIKE)
    private List<Long> roleIds;

    /**
     * 岗位
     */
    @Schema(title = "岗位")
    @ExcelProperty(value = "岗位", order = 6)
    @QueryColumn(QueryType.LIKE)
    private List<Long> postIds;

    /**
     * 是否启用
     */
    @Schema(title = "是否启用")
    @ExcelProperty(value = "是否启用", order = 7)
    @QueryColumn(QueryType.EQUAL)
    private Boolean enable;

    /**
     * 排序
     */
    @Schema(title = "排序")
    @ExcelProperty(value = "排序", order = 8)
    @QueryColumn(QueryType.EQUAL)
    private Integer sort;

    /**
     * 最后登录时间
     */
    @Schema(title = "最后登录时间")
    @ExcelProperty(value = "最后登录时间", order = 9)
    @QueryColumn(QueryType.BETWEEN)
    private LocalDateTime lastLoginTime;

    /**
     * 是否删除
     */
    @Schema(title = "是否删除")
    private Boolean deleted;

    /**
     * 最后登录时间开始
     */
    private LocalDateTime lastLoginTimeStart;

    /**
     * 最后登录时间结束
     */
    private LocalDateTime lastLoginTimeEnd;

    public SysUser toSysUser() {
        return BeanUtil.copyProperties(this, SysUser.class);
    }
}
