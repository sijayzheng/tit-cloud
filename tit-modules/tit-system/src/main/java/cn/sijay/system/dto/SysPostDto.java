package cn.sijay.system.dto;

import cn.sijay.common.core.utils.BeanUtil;
import cn.sijay.common.mybatis.annotation.QueryColumn;
import cn.sijay.common.mybatis.dictionary.QueryType;
import cn.sijay.system.entity.SysPost;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * <strong>SysPost</strong>
 * <p>
 * 岗位信息 实体类
 * </p>
 *
 * @author sijay
 * @since 2024-04-25
 */
@Data
@Accessors(chain = true)
@Schema(name = "SysPost", title = "岗位信息", description = "岗位信息")
public class SysPostDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Schema(title = "id")
    private Long id;

    /**
     * 岗位名称
     */
    @Schema(title = "岗位名称")
    @ExcelProperty(value = "岗位名称", order = 0)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 50, message = "字段[岗位名称]最大长度为{max}")
    private String name;

    /**
     * 岗位编码
     */
    @Schema(title = "岗位编码")
    @ExcelProperty(value = "岗位编码", order = 1)
    @QueryColumn(QueryType.LIKE)
    @Size(max = 50, message = "字段[岗位编码]最大长度为{max}")
    private String code;

    /**
     * 排序
     */
    @Schema(title = "排序")
    @ExcelProperty(value = "排序", order = 2)
    @QueryColumn(QueryType.EQUAL)
    private Integer sort;

    /**
     * 是否删除
     */
    @Schema(title = "是否删除")
    private Boolean deleted;

    public SysPost toSysPost() {
        return BeanUtil.copyProperties(this, SysPost.class);
    }
}
