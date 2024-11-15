package cn.sijay.system.dto;

import cn.sijay.common.core.utils.BeanUtil;
import cn.sijay.common.mybatis.annotation.QueryColumn;
import cn.sijay.common.mybatis.dictionary.QueryType;
import cn.sijay.system.entity.SysConfig;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * <strong>SysConfig</strong>
 * <p>
 * 系统配置 实体类
 * </p>
 *
 * @author sijay
 * @since 2024-04-25
 */
@Data
@Accessors(chain = true)
@Schema(name = "SysConfig", title = "系统配置", description = "系统配置")
public class SysConfigDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Schema(title = "id")
    private Long id;

    /**
     * 配置名称
     */
    @Schema(title = "配置名称")
    @ExcelProperty(value = "配置名称", order = 0)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 50, message = "字段[配置名称]最大长度为{max}")
    private String name;

    /**
     * 配置编码
     */
    @Schema(title = "配置编码")
    @ExcelProperty(value = "配置编码", order = 1)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 50, message = "字段[配置编码]最大长度为{max}")
    private String code;

    /**
     * 配置值
     */
    @Schema(title = "配置值")
    @ExcelProperty(value = "配置值", order = 2)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 50, message = "字段[配置值]最大长度为{max}")
    private String value;

    /**
     * 排序
     */
    @Schema(title = "排序")
    @ExcelProperty(value = "排序", order = 3)
    @QueryColumn(QueryType.EQUAL)
    private Integer sort;

    /**
     * 是否删除
     */
    @Schema(title = "是否删除")
    private Boolean deleted;

    public SysConfig toSysConfig() {
        return BeanUtil.copyProperties(this, SysConfig.class);
    }
}
