package cn.sijay.system.dto;

import cn.sijay.common.core.utils.BeanUtil;
import cn.sijay.common.mybatis.annotation.QueryColumn;
import cn.sijay.common.mybatis.dictionary.QueryType;
import cn.sijay.system.entity.SysModule;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * <strong>SysModule</strong>
 * <p>
 * 系统模块 实体类
 * </p>
 *
 * @author sijay
 * @since 2024-04-28
 */
@Data
@Accessors(chain = true)
@Schema(name = "SysModule", title = "系统模块", description = "系统模块")
public class SysModuleDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Schema(title = "id")
    private Long id;

    /**
     * 模块名称
     */
    @Schema(title = "模块名称")
    @ExcelProperty(value = "模块名称", order = 0)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 50, message = "字段[模块名称]最大长度为{max}")
    private String name;

    /**
     * 菜单
     */
    @Schema(title = "菜单")
    @ExcelProperty(value = "菜单", order = 1)
    @QueryColumn(QueryType.LIKE)
    private List<Long> menuIds;

    public SysModule toSysModule() {
        return BeanUtil.copyProperties(this, SysModule.class);
    }
}
