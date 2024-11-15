package cn.sijay.system.entity;

import cn.sijay.common.core.entity.SelectOption;
import cn.sijay.common.core.utils.BeanUtil;
import cn.sijay.common.mybatis.annotation.QueryColumn;
import cn.sijay.common.mybatis.dictionary.DisplayType;
import cn.sijay.common.mybatis.dictionary.QueryType;
import cn.sijay.common.web.base.BaseEntity;
import cn.sijay.system.dto.SysDictDataDto;
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
 * <strong>SysDictData</strong>
 * <p>
 * 字典数据 实体类
 * </p>
 *
 * @author sijay
 * @since 2024-04-28
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_dict_data")
@Schema(name = "SysDictData", title = "字典数据", description = "字典数据")
public class SysDictData extends BaseEntity {

    /**
     * id
     */
    @Schema(title = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 字典类型
     */
    @Schema(title = "字典类型")
    @QueryColumn(QueryType.LIKE)
    @TableField("type_code")
    private String typeCode;

    /**
     * 标签
     */
    @Schema(title = "标签")
    @QueryColumn(QueryType.LIKE)
    @TableField("label")
    private String label;

    /**
     * 值
     */
    @Schema(title = "值")
    @QueryColumn(QueryType.LIKE)
    @TableField("value")
    private String value;

    /**
     * 字典排序
     */
    @Schema(title = "字典排序")
    @TableField("sort")
    private Integer sort;

    /**
     * 样式属性
     */
    @Schema(title = "样式属性")
    @QueryColumn(QueryType.LIKE)
    @TableField("css_class")
    private String cssClass;

    /**
     * 回显样式
     */
    @Schema(title = "回显样式")
    @QueryColumn(QueryType.LIKE)
    @TableField("display_type")
    private DisplayType displayType;

    /**
     * 是否默认
     */
    @Schema(title = "是否默认")
    @QueryColumn(QueryType.EQUAL)
    @TableField("defaults")
    private Boolean defaults;

    public SysDictDataDto toSysDictDataDto() {
        return BeanUtil.copyProperties(this, SysDictDataDto.class);
    }

    public SelectOption<String> toSelectOption() {
        return new SelectOption<>(label, label);
    }
}
