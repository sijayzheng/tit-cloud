package cn.sijay.gen.entity;

import cn.sijay.common.mybatis.dictionary.InputType;
import cn.sijay.common.mybatis.dictionary.JavaType;
import cn.sijay.common.mybatis.dictionary.QueryType;
import cn.sijay.common.web.base.BaseEntity;
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
 * <strong>GenTableColumn</strong>
 * <p>
 * 列信息
 * </p>
 *
 * @author sijay
 * @since 2024-04-12
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gen_table_column")
@Schema(name = "GenTableColumn", title = "列信息", description = "列信息")
public class GenTableColumn extends BaseEntity {
    /**
     * id
     */
    @Schema(title = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 表id
     */
    @Schema(title = "表id")
    @TableField("table_id")
    private Long tableId;

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
    @TableField("data_type")
    private String dataType;

    /**
     * 长度
     */
    @Schema(title = "长度")
    @TableField("length")
    private Integer length;

    /**
     * 字段类型
     */
    @Schema(title = "字段类型")
    @TableField("java_type")
    private JavaType javaType;

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
    private boolean primaryKey;

    /**
     * 是否唯一
     */
    @Schema(title = "是否唯一")
    @TableField("unique_key")
    private boolean uniqueKey;

    /**
     * 是否必填
     */
    @Schema(title = "是否必填")
    @TableField("required")
    private boolean required;

    /**
     * 是否可添加
     */
    @Schema(title = "是否可添加")
    @TableField("addable")
    private boolean addable;

    /**
     * 是否可编辑
     */
    @Schema(title = "是否可编辑")
    @TableField("editable")
    private boolean editable;

    /**
     * 是否父类字段
     */
    @Schema(title = "是否父类字段")
    @TableField("super_column")
    private boolean superColumn;

    /**
     * 是否是导入导出字段
     */
    @Schema(title = "是否是导入导出字段")
    @TableField("excel_column")
    private boolean excelColumn;

    /**
     * 是否显示
     */
    @Schema(title = "是否显示")
    @TableField("visible")
    private boolean visible;

    /**
     * 是否可查询
     */
    @Schema(title = "是否可查询")
    @TableField("queryable")
    private boolean queryable;

    /**
     * 查询方式
     */
    @Schema(title = "查询方式")
    @TableField("query_type")
    private QueryType queryType;

    /**
     * 输入类型
     */
    @Schema(title = "输入类型")
    @TableField("input_type")
    private InputType inputType;

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

    @TableField(exist = false)
    private String getterName;

    @TableField(exist = false)
    private Boolean unmodified;

}
