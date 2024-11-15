package cn.sijay.gen.entity.vo;

import cn.sijay.common.mybatis.annotation.QueryColumn;
import cn.sijay.common.mybatis.dictionary.QueryType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <strong>GenTableVo</strong>
 * <p>
 * 表信息
 * </p>
 *
 * @author sijay
 * @since 2024-04-12
 */
@Data
@Accessors(chain = true)
@Schema(name = "GenTableVo", title = "表信息", description = "表信息")
public class GenTableVo implements Serializable {

    /**
     * id
     */
    @Schema(title = "id")
    private Long id;

    /**
     * 表名
     */
    @Schema(title = "表名")
    @QueryColumn(QueryType.LIKE)
    private String tableName;

    /**
     * 表备注
     */
    @Schema(title = "表备注")
    @QueryColumn(QueryType.LIKE)
    private String tableComment;

    /**
     * 实体类名称
     */
    @Schema(title = "实体类名称")
    private String className;

    /**
     * 模板类型
     */
    @Schema(title = "模板类型")
    private String templateType;

    /**
     * 包路径
     */
    @Schema(title = "包路径")
    private String packageName;

    /**
     * 模块名
     */
    @Schema(title = "模块名")
    private String moduleName;

    /**
     * 生成业务名
     */
    @Schema(title = "生成业务名")
    private String businessName;

    /**
     * 生成功能名
     */
    @Schema(title = "生成功能名")
    private String functionName;

    /**
     * 作者
     */
    @Schema(title = "作者")
    private String author;

    /**
     * 生成方式
     */
    @Schema(title = "生成方式")
    private String genType;

    /**
     * 生成路径
     */
    @Schema(title = "生成路径")
    private String genPath;

    /**
     * 父类
     */
    @Schema(title = "父类")
    private String superClass;

    /**
     * 所属菜单
     */
    @Schema(title = "所属菜单")
    private Long menuId;

}
