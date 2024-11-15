package cn.sijay.system.service;

import cn.sijay.common.core.entity.TreeNode;
import cn.sijay.common.core.enums.ExceptionEnum;
import cn.sijay.common.core.exception.BaseException;
import cn.sijay.common.core.utils.StringUtil;
import cn.sijay.common.mybatis.base.IBaseService;
import cn.sijay.system.dto.RouteDto;
import cn.sijay.system.dto.SysMenuDto;
import cn.sijay.system.entity.SysMenu;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <strong>ISysMenuService</strong>
 * <p>
 * 菜单信息服务层
 * </p>
 *
 * @author sijay
 * @since 2024-04-28
 */
public interface ISysMenuService extends IBaseService<SysMenu, SysMenuDto> {

    /**
     * 唯一性校验
     *
     * @param dto 待校验的对象
     */
    default void checkUnique(SysMenuDto dto) {
    }

    /**
     * 校验是否存在
     *
     * @param id 待校验的对象ID
     */
    default void exists(Long id) {
        if (!exists(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getId, id))) {
            throw new BaseException(ExceptionEnum.DATA_NOT_FOUND, id);
        }
    }

    /**
     * 构建查询条件构造器
     *
     * @param dto 实体类
     * @return 查询条件构造器
     */
    default LambdaQueryWrapper<SysMenu> buildLambdaQueryWrapper(SysMenuDto dto) {
        LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(dto.getParentId() != null, SysMenu::getParentId, dto.getParentId());
        wrapper.like(StringUtil.isNotBlank(dto.getName()), SysMenu::getName, dto.getName());
        wrapper.like(dto.getType() != null, SysMenu::getType, dto.getType());
        wrapper.like(StringUtil.isNotBlank(dto.getPath()), SysMenu::getPath, dto.getPath());
        wrapper.like(StringUtil.isNotBlank(dto.getComponent()), SysMenu::getComponent, dto.getComponent());
        wrapper.like(StringUtil.isNotBlank(dto.getQueryParam()), SysMenu::getQueryParam, dto.getQueryParam());
        wrapper.like(StringUtil.isNotBlank(dto.getPerms()), SysMenu::getPerms, dto.getPerms());
        wrapper.like(StringUtil.isNotBlank(dto.getIcon()), SysMenu::getIcon, dto.getIcon());
        wrapper.eq(dto.getSort() != null, SysMenu::getSort, dto.getSort());
        wrapper.eq(dto.getLink() != null, SysMenu::getLink, dto.getLink());
        wrapper.eq(dto.getCache() != null, SysMenu::getCache, dto.getCache());
        wrapper.eq(dto.getVisible() != null, SysMenu::getVisible, dto.getVisible());
        wrapper.eq(dto.getEnabled() != null, SysMenu::getEnabled, dto.getEnabled());
        wrapper.orderByAsc(SysMenu::getSort);
        return wrapper;
    }

    /**
     * 导入
     *
     * @param file 要导入的excel文件。
     * @return 是否导入成功
     */
    String importData(MultipartFile file);

    /**
     * 导出
     */
    void export(HttpServletResponse response);

    List<SysMenu> selectMenuTreeByUserId(Long userId);

    List<RouteDto> buildMenus(List<SysMenu> menus);

    List<TreeNode<Long>> getMenuTreeOptions();
}
