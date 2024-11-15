package cn.sijay.system.dto;

import cn.sijay.common.core.utils.BeanUtil;
import cn.sijay.common.mybatis.annotation.QueryColumn;
import cn.sijay.common.mybatis.dictionary.QueryType;
import cn.sijay.system.entity.SysDictType;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * <strong>SysDictType</strong>
 * <p>
 * 字典类型 实体类
 * </p>
 *
 * @author sijay
 * @since 2024-04-28
 */
@Data
@Accessors(chain = true)
@Schema(name = "SysDictType", title = "字典类型", description = "字典类型")
public class SysDictTypeDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Schema(title = "id")
    private Long id;

    /**
     * 字典名称
     */
    @Schema(title = "字典名称")
    @ExcelProperty(value = "字典名称", order = 0)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 100, message = "字段[字典名称]最大长度为{max}")
    private String name;

    /**
     * 字典编码
     */
    @Schema(title = "字典编码")
    @ExcelProperty(value = "字典编码", order = 1)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 100, message = "字段[字典编码]最大长度为{max}")
    private String code;

    /**
     * 内置
     */
    @Schema(title = "内置")
    @ExcelProperty(value = "内置", order = 2)
    @QueryColumn(QueryType.EQUAL)
    private Boolean internal;

    public SysDictType toSysDictType() {
        return BeanUtil.copyProperties(this, SysDictType.class);
    }
}
