package cn.sijay.gen.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <strong>GenTableColumnVo</strong>
 * <p>
 * 列信息
 * </p>
 *
 * @author sijay
 * @since 2024-04-12
 */
@Data
@Accessors(chain = true)
@Schema(name = "GenTableColumnVo", title = "列信息", description = "列信息")
public class GenTableColumnVo implements Serializable {

    /**
     * id
     */
    @Schema(title = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 列名
     */
    @Schema(title = "列名")
    @TableField("column_name")
    private String columnName;
    /**
     * 列注释
     */
    @Schema(title = "列注释")
    @TableField("column_comment")
    private String columnComment;
    /**
     * 列类型
     */
    @Schema(title = "列类型")
    @TableField("column_type")
    private String columnType;
    /**
     * 长度
     */
    @Schema(title = "长度")
    @TableField("length")
    private Integer length;
    /**
     * 小数位
     */
    @Schema(title = "小数位")
    @TableField("scale")
    private Integer scale;
    /**
     * 字段类型
     */
    @Schema(title = "字段类型")
    @TableField("java_type")
    private String javaType;
    /**
     * 字段名称
     */
    @Schema(title = "字段名称")
    @TableField("field_name")
    private String fieldName;
    /**
     * 是否主键
     */
    @Schema(title = "是否主键")
    @TableField("primary_key")
    private String primaryKey;
    /**
     * 是否必填
     */
    @Schema(title = "是否必填")
    @TableField("required")
    private String required;
    /**
     * 是否可添加
     */
    @Schema(title = "是否可添加")
    @TableField("addable")
    private String addable;
    /**
     * 是否可编辑
     */
    @Schema(title = "是否可编辑")
    @TableField("editable")
    private String editable;
    /**
     * 是否父类字段
     */
    @Schema(title = "是否父类字段")
    @TableField("super_column")
    private String superColumn;
    /**
     * 是否唯一
     */
    @Schema(title = "是否唯一")
    @TableField("unique_key")
    private String uniqueKey;
    /**
     * 是否是导入导出字段
     */
    @Schema(title = "是否是导入导出字段")
    @TableField("excel_column")
    private String excelColumn;
    /**
     * 是否显示
     */
    @Schema(title = "是否显示")
    @TableField("visible")
    private String visible;
    /**
     * 是否可查询
     */
    @Schema(title = "是否可查询")
    @TableField("queryable")
    private String queryable;
    /**
     * 查询方式
     */
    @Schema(title = "查询方式")
    @TableField("query_type")
    private String queryType;
    /**
     * 输入类型
     */
    @Schema(title = "输入类型")
    @TableField("input_type")
    private String inputType;
    /**
     * 数据字典
     */
    @Schema(title = "数据字典")
    @TableField("dict_type")
    private String dictType;
    /**
     * 排序
     */
    @Schema(title = "排序")
    @TableField("sort")
    private Integer sort;

}
