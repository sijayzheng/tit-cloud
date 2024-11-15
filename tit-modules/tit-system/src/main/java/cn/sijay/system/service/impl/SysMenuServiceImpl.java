package cn.sijay.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.sijay.common.core.entity.Id;
import cn.sijay.common.core.entity.TreeNode;
import cn.sijay.common.core.enums.ExceptionEnum;
import cn.sijay.common.core.exception.BaseException;
import cn.sijay.common.core.utils.BeanUtil;
import cn.sijay.common.core.utils.StringUtil;
import cn.sijay.common.core.utils.TreeUtil;
import cn.sijay.common.mybatis.dictionary.MenuType;
import cn.sijay.common.mybatis.entity.PageQuery;
import cn.sijay.common.satoken.LoginHelper;
import cn.sijay.common.web.utils.ExcelUtil;
import cn.sijay.system.dto.MetaDto;
import cn.sijay.system.dto.RouteDto;
import cn.sijay.system.dto.SysMenuDto;
import cn.sijay.system.entity.SysMenu;
import cn.sijay.system.entity.SysRole;
import cn.sijay.system.mapper.SysMenuMapper;
import cn.sijay.system.service.ISysMenuService;
import cn.sijay.system.service.ISysRoleService;
import cn.sijay.system.service.ISysUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <strong>SysMenuService</strong>
 * <p>
 * 菜单信息服务层实现类
 * </p>
 *
 * @author sijay
 * @since 2024-04-28
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {
    private final ISysUserService sysUserService;
    private final ISysRoleService sysRoleService;

    /**
     * 分页查询
     *
     * @param dto       数据传输对象
     * @param pageQuery 分页查询条件
     * @return 分页查询结果
     */
    @Override
    public IPage<SysMenuDto> page(SysMenuDto dto, PageQuery pageQuery) {
        return page(pageQuery.build(), buildQueryWrapper(dto, SysMenuDto.class)).convert(SysMenu::toSysMenuDto);
    }

    /**
     * 新增
     *
     * @param dto 数据传输对象
     * @return 新增结果，true为成功，false为失败
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean add(SysMenuDto dto) {
        checkUnique(dto);
        return save(dto.toSysMenu());
    }

    /**
     * 更新
     *
     * @param dto 数据传输对象
     * @return 更新结果，true为成功，false为失败
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean update(SysMenuDto dto) {
        checkUnique(dto);
        exists(dto.getId());
        SysMenu sysMenu = getById(dto.getId());
        BeanUtil.copyProperties(dto, sysMenu);
        return updateById(sysMenu);
    }

    /**
     * 删除
     *
     * @param id id
     * @return 删除结果，true为成功，false为失败
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean remove(Id<Long> id) {
        if (ObjectUtils.isNotEmpty(id.getId())) {
            exists(id.getId());
            return removeById(id.getId());
        } else if (CollectionUtils.isNotEmpty(id.getIds())) {
            return removeByIds(id.getIds());
        } else {
            throw new BaseException(ExceptionEnum.REQUEST_PARAM_ERROR);
        }
    }

    /**
     * 导入
     *
     * @param file 要导入的excel文件。
     * @return 是否导入成功
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public String importData(MultipartFile file) {
        int successCount = 0;
        int failCount = 0;
        for (SysMenuDto dto : ExcelUtil.read(file, SysMenuDto.class)) {
            if (add(dto)) {
                successCount++;
            } else {
                failCount++;
            }
        }
        log.info("导入成功{}条，失败{}条", successCount, failCount);
        return StringUtil.format("导入成功{}条，失败{}条", successCount, failCount);
    }

    /**
     * 导出
     */
    @Override
    public void export(HttpServletResponse response) {
        ExcelUtil.export(list(), "菜单信息", SysMenu.class, response);
    }

    /**
     * 根据用户ID查询菜单
     *
     * @param userId 用户名称
     * @return 菜单列表
     */
    @Override
    public List<SysMenu> selectMenuTreeByUserId(Long userId) {
        List<SysMenu> menus;
        if (LoginHelper.isSuperAdmin(userId)) {
            LambdaQueryWrapper<SysMenu> lqw = new LambdaQueryWrapper<SysMenu>()
                    .in(SysMenu::getType, MenuType.DIRECTORY, MenuType.MENU)
                    .eq(SysMenu::getEnabled, true)
                    .orderByAsc(SysMenu::getParentId)
                    .orderByAsc(SysMenu::getSort);
            menus = baseMapper.selectList(lqw);
        } else {
            Set<Long> menuIds = sysRoleService.listByIds(sysUserService.getById(userId).getRoleIds())
                                              .stream()
                                              .map(SysRole::getMenuIds)
                                              .flatMap(Collection::stream)
                                              .collect(Collectors.toSet());
            menus = baseMapper.selectBatchIds(menuIds);
        }
        return getChildPerms(menus, 0);
    }

    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    @Override
    public List<RouteDto> buildMenus(List<SysMenu> menus) {
        List<RouteDto> routers = new ArrayList<>();
        for (SysMenu menu : menus) {
            RouteDto router = new RouteDto();
            router.setHidden(menu.getVisible());
            router.setName(menu.getRouteName());
            router.setPath(menu.getRouterPath());
            router.setComponent(menu.getComponentInfo());
            router.setQuery(menu.getQueryParam());
            router.setMeta(new MetaDto(menu.getName(), menu.getIcon(), menu.getCache(), menu.getPath()));
            List<SysMenu> cMenus = menu.getChildren();
            if (CollUtil.isNotEmpty(cMenus) && MenuType.DIRECTORY.equals(menu.getType())) {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            } else if (menu.isMenuFrame()) {
                router.setMeta(null);
                List<RouteDto> childrenList = new ArrayList<>();
                RouteDto children = new RouteDto();
                children.setPath(menu.getPath());
                children.setComponent(menu.getComponent());
                children.setName(StringUtil.capitalize(menu.getPath()));
                children.setMeta(new MetaDto(menu.getName(), menu.getIcon(), menu.getCache(), menu.getPath()));
                children.setQuery(menu.getQueryParam());
                childrenList.add(children);
                router.setChildren(childrenList);
            } else if (menu.getParentId().intValue() == 0 && menu.isInnerLink()) {
                router.setMeta(new MetaDto(menu.getName(), menu.getIcon()));
                router.setPath("/");
                List<RouteDto> childrenList = new ArrayList<>();
                RouteDto children = new RouteDto();
                String routerPath = SysMenu.innerLinkReplaceEach(menu.getPath());
                children.setPath(routerPath);
                children.setComponent("InnerLink");
                children.setName(StringUtil.capitalize(routerPath));
                children.setMeta(new MetaDto(menu.getName(), menu.getIcon(), menu.getPath()));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
    }

    @Override
    public List<TreeNode<Long>> getMenuTreeOptions() {
        LambdaQueryWrapper<SysMenu> lqw = new LambdaQueryWrapper<SysMenu>()
                .in(SysMenu::getType, MenuType.DIRECTORY, MenuType.MENU)
                .eq(SysMenu::getEnabled, true)
                .orderByAsc(SysMenu::getParentId)
                .orderByAsc(SysMenu::getSort);
        List<SysMenu> menus = baseMapper.selectList(lqw);
        return TreeUtil.buildTree(menus, 0L, (item, treeNode) -> treeNode.setId(item.getId()).setPid(item.getParentId())
                                                                         .setName(item.getName()).setSort(item.getSort()));
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list     分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    private List<SysMenu> getChildPerms(List<SysMenu> list, int parentId) {
        List<SysMenu> returnList = new ArrayList<>();
        for (SysMenu t : list) {
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == parentId) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<SysMenu> list, SysMenu t) {
        // 得到子节点列表
        List<SysMenu> childList = list.stream().filter(n -> n.getParentId().equals(t.getId())).collect(Collectors.toList());
        t.setChildren(childList);
        for (SysMenu tChild : childList) {
            // 判断是否有子节点
            if (list.stream().anyMatch(n -> n.getParentId().equals(tChild.getId()))) {
                recursionFn(list, tChild);
            }
        }
    }
}
