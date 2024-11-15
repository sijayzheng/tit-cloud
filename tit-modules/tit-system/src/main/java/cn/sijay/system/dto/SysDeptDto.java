package cn.sijay.system.dto;

import cn.sijay.common.core.utils.BeanUtil;
import cn.sijay.common.mybatis.annotation.QueryColumn;
import cn.sijay.common.mybatis.dictionary.QueryType;
import cn.sijay.system.entity.SysDept;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

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
@Accessors(chain = true)
@Schema(name = "SysDept", title = "部门信息", description = "部门信息")
public class SysDeptDto implements Serializable {

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
     * 部门名称
     */
    @Schema(title = "部门名称")
    @ExcelProperty(value = "部门名称", order = 1)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 50, message = "字段[部门名称]最大长度为{max}")
    private String name;

    /**
     * 部门负责人
     */
    @Schema(title = "部门负责人")
    @ExcelProperty(value = "部门负责人", order = 2)
    @QueryColumn(QueryType.EQUAL)
    private Long leader;

    /**
     * 部门电话
     */
    @Schema(title = "部门电话")
    @ExcelProperty(value = "部门电话", order = 3)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 11, message = "字段[部门电话]最大长度为{max}")
    private String phone;

    /**
     * 排序
     */
    @Schema(title = "排序")
    @ExcelProperty(value = "排序", order = 4)
    @QueryColumn(QueryType.EQUAL)
    private Integer sort;

    /**
     * 是否删除
     */
    @Schema(title = "是否删除")
    private Boolean deleted;

    public SysDept toSysDept() {
        return BeanUtil.copyProperties(this, SysDept.class);
    }
}
