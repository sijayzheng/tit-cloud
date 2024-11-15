package cn.sijay.system.entity;

import cn.sijay.common.core.utils.BeanUtil;
import cn.sijay.common.mybatis.annotation.QueryColumn;
import cn.sijay.common.mybatis.dictionary.QueryType;
import cn.sijay.common.web.base.BaseEntity;
import cn.sijay.system.dto.SysModuleDto;
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
 * <strong>SysModule</strong>
 * <p>
 * 系统模块 实体类
 * </p>
 *
 * @author sijay
 * @since 2024-04-28
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_module")
@Schema(name = "SysModule", title = "系统模块", description = "系统模块")
public class SysModule extends BaseEntity {

    /**
     * id
     */
    @Schema(title = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 模块名称
     */
    @Schema(title = "模块名称")
    @QueryColumn(QueryType.LIKE)
    @TableField("name")
    private String name;

    /**
     * 菜单
     */
    @Schema(title = "菜单")
    @QueryColumn(QueryType.LIKE)
    @TableField("menu_ids")
    private List<Long> menuIds;


    public SysModuleDto toSysModuleDto () {
        return BeanUtil.copyProperties(this, SysModuleDto.class);
    }
}
