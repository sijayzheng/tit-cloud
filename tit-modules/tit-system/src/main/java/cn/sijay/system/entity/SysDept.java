package cn.sijay.system.entity;

import cn.sijay.common.core.utils.BeanUtil;
import cn.sijay.common.mybatis.annotation.QueryColumn;
import cn.sijay.common.mybatis.dictionary.QueryType;
import cn.sijay.common.web.base.BaseEntityWithVersion;
import cn.sijay.system.dto.SysDeptDto;
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
 * <strong>SysDept</strong>
 * <p>
 * 部门信息 实体类
 * </p>
 *
 * @author sijay
 * @since 2024-04-25
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_dept")
@Schema(name = "SysDept", title = "部门信息", description = "部门信息")
public class SysDept extends BaseEntityWithVersion {

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
     * 部门名称
     */
    @Schema(title = "部门名称")
    @QueryColumn(QueryType.LIKE)
    @TableField("name")
    private String name;

    /**
     * 部门负责人
     */
    @Schema(title = "部门负责人")
    @QueryColumn(QueryType.EQUAL)
    @TableField("leader")
    private Long leader;

    /**
     * 部门电话
     */
    @Schema(title = "部门电话")
    @QueryColumn(QueryType.LIKE)
    @TableField("phone")
    private String phone;

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

    public SysDeptDto toSysDeptDto() {
        return BeanUtil.copyProperties(this, SysDeptDto.class);
    }
}
