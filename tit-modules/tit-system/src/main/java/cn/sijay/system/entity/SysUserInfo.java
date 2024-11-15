package cn.sijay.system.entity;

import cn.sijay.common.core.utils.BeanUtil;
import cn.sijay.common.mybatis.annotation.QueryColumn;
import cn.sijay.common.mybatis.dictionary.QueryType;
import cn.sijay.common.web.base.BaseEntityWithVersion;
import cn.sijay.system.dto.SysUserInfoDto;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * <strong>SysUserInfo</strong>
 * <p>
 * 用户信息 实体类
 * </p>
 *
 * @author sijay
 * @since 2024-04-25
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user_info")
@Schema(name = "SysUserInfo", title = "用户信息", description = "用户信息")
public class SysUserInfo extends BaseEntityWithVersion {

    /**
     * id
     */
    @Schema(title = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户
     */
    @Schema(title = "用户")
    @QueryColumn(QueryType.EQUAL)
    @TableField("user_id")
    private Long userId;

    /**
     * 姓名
     */
    @Schema(title = "姓名")
    @QueryColumn(QueryType.LIKE)
    @TableField("name")
    private String name;

    /**
     * 性别
     */
    @Schema(title = "性别")
    @QueryColumn(QueryType.LIKE)
    @TableField("gender")
    private String gender;

    /**
     * 生日
     */
    @Schema(title = "生日")
    @QueryColumn(QueryType.BETWEEN)
    @TableField("birthday")
    private LocalDate birthday;

    /**
     * 头像
     */
    @Schema(title = "头像")
    @QueryColumn(QueryType.LIKE)
    @TableField("avatar")
    private String avatar;

    /**
     * 省
     */
    @Schema(title = "省")
    @QueryColumn(QueryType.EQUAL)
    @TableField("province")
    private Long province;

    /**
     * 市
     */
    @Schema(title = "市")
    @QueryColumn(QueryType.EQUAL)
    @TableField("city")
    private Long city;

    /**
     * 区
     */
    @Schema(title = "区")
    @QueryColumn(QueryType.EQUAL)
    @TableField("area")
    private Long area;

    /**
     * 详细地址
     */
    @Schema(title = "详细地址")
    @QueryColumn(QueryType.LIKE)
    @TableField("address")
    private String address;

    /**
     * 备注
     */
    @Schema(title = "备注")
    @QueryColumn(QueryType.LIKE)
    @TableField("remark")
    private String remark;

    /**
     * 是否删除
     */
    @Schema(title = "是否删除")
    @TableField("deleted")
    private Boolean deleted;

    /**
     * 生日开始
     */
    @TableField(exist = false)
    private LocalDate birthdayStart;

    /**
     * 生日结束
     */
    @TableField(exist = false)
    private LocalDate birthdayEnd;

    public SysUserInfoDto toSysUserInfoDto() {
        return BeanUtil.copyProperties(this, SysUserInfoDto.class);
    }
}
