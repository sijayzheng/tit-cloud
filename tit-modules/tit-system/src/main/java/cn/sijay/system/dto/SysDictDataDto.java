package cn.sijay.system.dto;

import cn.sijay.common.core.utils.BeanUtil;
import cn.sijay.common.mybatis.annotation.QueryColumn;
import cn.sijay.common.mybatis.dictionary.DisplayType;
import cn.sijay.common.mybatis.dictionary.QueryType;
import cn.sijay.system.entity.SysDictData;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * <strong>SysDictData</strong>
 * <p>
 * 字典数据 实体类
 * </p>
 *
 * @author sijay
 * @since 2024-04-28
 */
@Data
@Accessors(chain = true)
@Schema(name = "SysDictData", title = "字典数据", description = "字典数据")
public class SysDictDataDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Schema(title = "id")
    private Long id;

    /**
     * 字典类型
     */
    @Schema(title = "字典类型")
    @ExcelProperty(value = "字典类型", order = 0)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 100, message = "字段[字典类型]最大长度为{max}")
    private String typeCode;

    /**
     * 标签
     */
    @Schema(title = "标签")
    @ExcelProperty(value = "标签", order = 1)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 100, message = "字段[标签]最大长度为{max}")
    private String label;

    /**
     * 值
     */
    @Schema(title = "值")
    @ExcelProperty(value = "值", order = 2)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 100, message = "字段[值]最大长度为{max}")
    private String value;

    /**
     * 字典排序
     */
    @Schema(title = "字典排序")
    @ExcelProperty(value = "字典排序", order = 3)
    private Integer sort;

    /**
     * 样式属性
     */
    @Schema(title = "样式属性")
    @ExcelProperty(value = "样式属性", order = 4)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 100, message = "字段[样式属性]最大长度为{max}")
    private String cssClass;

    /**
     * 回显样式
     */
    @Schema(title = "回显样式")
    @ExcelProperty(value = "回显样式", order = 5)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 100, message = "字段[回显样式]最大长度为{max}")
    private DisplayType displayType;

    /**
     * 是否默认
     */
    @Schema(title = "是否默认")
    @ExcelProperty(value = "是否默认", order = 6)
    @QueryColumn(QueryType.EQUAL)
    private Boolean defaults;

    public SysDictData toSysDictData() {
        return BeanUtil.copyProperties(this, SysDictData.class);
    }
}
