package cn.sijay.system.dto;

import cn.sijay.common.core.utils.BeanUtil;
import cn.sijay.common.mybatis.annotation.QueryColumn;
import cn.sijay.common.mybatis.dictionary.QueryType;
import cn.sijay.system.entity.SysUserInfo;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
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
@Accessors(chain = true)
@Schema(name = "SysUserInfo", title = "用户信息", description = "用户信息")
public class SysUserInfoDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Schema(title = "id")
    private Long id;

    /**
     * 用户
     */
    @Schema(title = "用户")
    @ExcelProperty(value = "用户", order = 0)
    @QueryColumn(QueryType.EQUAL)
    private Long userId;

    /**
     * 姓名
     */
    @Schema(title = "姓名")
    @ExcelProperty(value = "姓名", order = 1)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 50, message = "字段[姓名]最大长度为{max}")
    private String name;

    /**
     * 性别
     */
    @Schema(title = "性别")
    @ExcelProperty(value = "性别", order = 2)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 1, message = "字段[性别]最大长度为{max}")
    private String gender;

    /**
     * 生日
     */
    @Schema(title = "生日")
    @ExcelProperty(value = "生日", order = 3)
    @QueryColumn(QueryType.BETWEEN)
    private LocalDate birthday;

    /**
     * 头像
     */
    @Schema(title = "头像")
    @ExcelProperty(value = "头像", order = 4)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 200, message = "字段[头像]最大长度为{max}")
    private String avatar;

    /**
     * 省
     */
    @Schema(title = "省")
    @ExcelProperty(value = "省", order = 5)
    @QueryColumn(QueryType.EQUAL)
    private Long province;

    /**
     * 市
     */
    @Schema(title = "市")
    @ExcelProperty(value = "市", order = 6)
    @QueryColumn(QueryType.EQUAL)
    private Long city;

    /**
     * 区
     */
    @Schema(title = "区")
    @ExcelProperty(value = "区", order = 7)
    @QueryColumn(QueryType.EQUAL)
    private Long area;

    /**
     * 详细地址
     */
    @Schema(title = "详细地址")
    @ExcelProperty(value = "详细地址", order = 8)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 200, message = "字段[详细地址]最大长度为{max}")
    private String address;

    /**
     * 备注
     */
    @Schema(title = "备注")
    @ExcelProperty(value = "备注", order = 9)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 200, message = "字段[备注]最大长度为{max}")
    private String remark;

    /**
     * 是否删除
     */
    @Schema(title = "是否删除")
    private Boolean deleted;

    /**
     * 生日开始
     */
    private LocalDate birthdayStart;

    /**
     * 生日结束
     */
    private LocalDate birthdayEnd;

    public SysUserInfo toSysUserInfo() {
        return BeanUtil.copyProperties(this, SysUserInfo.class);
    }
}
