package cn.sijay.system.entity;

import cn.sijay.common.core.utils.BeanUtil;
import cn.sijay.common.mybatis.annotation.QueryColumn;
import cn.sijay.common.mybatis.dictionary.QueryType;
import cn.sijay.common.web.base.BaseEntity;
import cn.sijay.system.dto.SysDictTypeDto;
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
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_dict_type")
@Schema(name = "SysDictType", title = "字典类型", description = "字典类型")
public class SysDictType extends BaseEntity {

    /**
     * id
     */
    @Schema(title = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 字典名称
     */
    @Schema(title = "字典名称")
    @QueryColumn(QueryType.LIKE)
    @TableField("name")
    private String name;

    /**
     * 字典编码
     */
    @Schema(title = "字典编码")
    @QueryColumn(QueryType.LIKE)
    @TableField("code")
    private String code;

    /**
     * 内置
     */
    @Schema(title = "内置")
    @QueryColumn(QueryType.EQUAL)
    @TableField("internal")
    private Boolean internal;

    @TableField(exist = false)
    private List<SysDictData> dictDatas;

    public SysDictTypeDto toSysDictTypeDto() {
        return BeanUtil.copyProperties(this, SysDictTypeDto.class);
    }
}
