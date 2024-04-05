package com.dermai.project.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.dermai.common.Utils.SecurityUtils;
import com.dermai.common.constants.Constants;
import com.dermai.common.constants.UserConstants;
import com.dermai.project.system.domain.SysMenu;
import com.dermai.project.system.domain.vo.MetaVo;
import com.dermai.project.system.domain.vo.RouterVo;
import com.dermai.project.system.mapper.SysMenuMapper;
import com.dermai.project.system.service.ISysMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Shaobo
 */
@Service
public class SysMenuServiceImpl implements ISysMenuService {

    @Autowired
    private SysMenuMapper menuMapper;

    /**
     * 根据角色ID查询权限
     *
     * @param roleId 角色ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectMenuPermsByRoleId(Long roleId) {
        List<String> perms = menuMapper.selectMenuPermsByRoleId(roleId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StrUtil.isNotEmpty(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    @Override
    public Collection<String> selectMenuPermsByUserId(Long userId) {
        List<String> perms = menuMapper.selectMenuPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StrUtil.isNotEmpty(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    @Override
    public List<SysMenu> selectMenuTreeByUserId(Long userId) {
        List<SysMenu> menus = null;
        if (SecurityUtils.isAdmin(userId)) {
            menus = menuMapper.selectMenuTreeAll();
        } else {
            menus = menuMapper.selectMenuTreeByUserId(userId);
        }
        return getChildPerms(menus, 0);
    }

    @Override
    public List<RouterVo> buildMenus(List<SysMenu> menus)
    {
        List<RouterVo> routers = new LinkedList<>();
        for (SysMenu menu : menus)
        {
            RouterVo router = new RouterVo();
            // set hidder
            router.setHidden("1".equals(menu.getVisible()));
            // get capitalized route name
            router.setName(getRouteName(menu));
            // get router path, if is parent type, add "/"
            router.setPath(getRouterPath(menu));
            // get component path
            router.setComponent(getComponent(menu));
            router.setQuery(menu.getQuery());
            // meta info
            router.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), StringUtils.equals("1", menu.getIsCache()), menu.getPath()));
            List<SysMenu> cMenus = menu.getChildren();
            // recursive set children
            if (cMenus != null && !cMenus.isEmpty() && UserConstants.TYPE_DIR.equals(menu.getMenuType())) {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            } else if (isMenuFrame(menu)) {
                // type is menu
                router.setMeta(null);
                List<RouterVo> childrenList = new ArrayList<RouterVo>();
                RouterVo children = new RouterVo();
                children.setPath(menu.getPath());
                children.setComponent(menu.getComponent());
                children.setName(StringUtils.capitalize(menu.getPath()));
                children.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), StringUtils.equals("1", menu.getIsCache()), menu.getPath()));
                children.setQuery(menu.getQuery());
                childrenList.add(children);
                router.setChildren(childrenList);
            } else if (menu.getParentId().intValue() == 0 && isInnerLink(menu)) {
                // is parent and is inner link
                router.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon()));
                router.setPath("/");
                List<RouterVo> childrenList = new ArrayList<>();
                RouterVo children = new RouterVo();
                String routerPath = innerLinkReplaceEach(menu.getPath());
                children.setPath(routerPath);
                children.setComponent(UserConstants.INNER_LINK);
                children.setName(StringUtils.capitalize(routerPath));
                children.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), menu.getPath()));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
    }

    public String getComponent(SysMenu menu) {
        String component = UserConstants.LAYOUT;
        // component path is not empty and is not menu
        if (StringUtils.isNotEmpty(menu.getComponent()) && !isMenuFrame(menu)) {
            component = menu.getComponent();
        }
        // component is empty, is not parent and is inner link, set component as inner link
        else if (StringUtils.isEmpty(menu.getComponent()) && menu.getParentId().intValue() != 0 && isInnerLink(menu)) {
            component = UserConstants.INNER_LINK;
        }
        // component is empty, is not parent, but is parent view
        else if (StringUtils.isEmpty(menu.getComponent()) && isParentView(menu)) {
            component = UserConstants.PARENT_VIEW;
        }
        return component;
    }

    public boolean isParentView(SysMenu menu) {
        return menu.getParentId().intValue() != 0 && UserConstants.TYPE_DIR.equals(menu.getMenuType());
    }

    private String getRouterPath(SysMenu menu) {
        String routerPath = menu.getPath();
        // is inner link, get rid of http(s):// and replace "." to "/"
        if (menu.getParentId().intValue() != 0 && isInnerLink(menu)) {
            routerPath = innerLinkReplaceEach(routerPath);
        }
        // is parent, is direction, and is inner link
        if (0 == menu.getParentId().intValue() && UserConstants.TYPE_DIR.equals(menu.getMenuType())
                && UserConstants.NO_FRAME.equals(menu.getIsFrame())) {
            routerPath = "/" + menu.getPath();
        }
        // is parent and is menu
        else if (isMenuFrame(menu)) {
            routerPath = "/";
        }
        return routerPath;
    }

    public String innerLinkReplaceEach(String routerPath) {
        return StringUtils.replaceEach(routerPath, new String[] { Constants.HTTP, Constants.HTTPS, Constants.WWW, ".", ":" },
                new String[] { "", "", "", "/", "/" });
    }

    private boolean isInnerLink(SysMenu menu) {
        // isFrame is 1 (not extarnal link) and start with http(s)
        return menu.getIsFrame().equals(UserConstants.NO_FRAME) && StrUtil.startWithAny(menu.getPath(), Constants.HTTP, Constants.HTTPS);
    }

    private String getRouteName(SysMenu menu) {
        String routerName = StrUtil.upperFirst(menu.getPath());
        // is external link
        if (isMenuFrame(menu)) {
            routerName = "";
        }
        return routerName;
    }

    private boolean isMenuFrame(SysMenu menu) {
        return menu.getParentId().intValue() == 0 && UserConstants.TYPE_MENU.equals(menu.getMenuType())
                && menu.getIsFrame().equals(UserConstants.NO_FRAME);
    }

    /**
     * get child perms
     * @param menus
     * @param parentId
     * @return
     */
    private List<SysMenu> getChildPerms(List<SysMenu> menus, int parentId) {
        List<SysMenu> returnList = new ArrayList<>();
        for (SysMenu menu : menus) {
            if (menu.getParentId() == parentId) {
                findParentChild(menu, menus);
                returnList.add(menu);
            }
        }
        return returnList;
    }

    private void findParentChild(SysMenu menu, List<SysMenu> menus) {
        List<SysMenu> childList = getChildList(menus, menu);
        menu.setChildren(childList);
        for (SysMenu tChild : childList) {
            if (hasChild(menus, tChild)) {
                findParentChild(tChild, menus);
            }
        }
    }

    private List<SysMenu> getChildList(List<SysMenu> list, SysMenu t)
    {
        List<SysMenu> tlist = new ArrayList<>();
        for (SysMenu n : list) {
            if (n.getParentId().longValue() == t.getMenuId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    private boolean hasChild(List<SysMenu> list, SysMenu t) {
        return !getChildList(list, t).isEmpty();
    }
}
