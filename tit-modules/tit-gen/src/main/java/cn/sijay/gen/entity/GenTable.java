package cn.sijay.gen.entity;

import cn.sijay.common.mybatis.dictionary.GenType;
import cn.sijay.common.mybatis.dictionary.SuperClassType;
import cn.sijay.common.mybatis.dictionary.TemplateType;
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

import java.util.List;
import java.util.Set;

/**
 * <strong>GenTable</strong>
 * <p>
 * 表信息
 * </p>
 *
 * @author sijay
 * @since 2024-04-12
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gen_table")
@Schema(name = "GenTable", title = "表信息", description = "表信息")
public class GenTable extends BaseEntity {

    /**
     * id
     */
    @Schema(title = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 表名
     */
    @Schema(title = "表名")
    @TableField("table_name")
    private String tableName;

    /**
     * 表备注
     */
    @Schema(title = "表备注")
    @TableField("table_comment")
    private String tableComment;

    /**
     * 实体类名称
     */
    @Schema(title = "实体类名称")
    @TableField("class_name")
    private String className;

    /**
     * 模板类型
     */
    @Schema(title = "模板类型")
    @TableField("template_type")
    private TemplateType templateType;

    /**
     * 包路径
     */
    @Schema(title = "包路径")
    @TableField("package_name")
    private String packageName;

    /**
     * 模块名
     */
    @Schema(title = "模块名")
    @TableField("module_name")
    private String moduleName;

    /**
     * 生成业务名
     */
    @Schema(title = "生成业务名")
    @TableField("business_name")
    private String businessName;

    /**
     * 生成功能名
     */
    @Schema(title = "生成功能名")
    @TableField("function_name")
    private String functionName;

    /**
     * 作者
     */
    @Schema(title = "作者")
    @TableField("author")
    private String author;

    /**
     * 生成方式
     */
    @Schema(title = "生成方式")
    @TableField("gen_type")
    private GenType genType;

    /**
     * 生成路径
     */
    @Schema(title = "生成路径")
    @TableField("gen_path")
    private String genPath;

    /**
     * 父类
     */
    @Schema(title = "父类")
    @TableField("super_class")
    private SuperClassType superClass;

    /**
     * 所属菜单
     */
    @Schema(title = "所属菜单")
    @TableField("menu_id")
    private Long menuId;

    @TableField(exist = false)
    private List<GenTableColumn> fields;
    @TableField(exist = false)
    private Set<String> imports;
}
