package com.dermai.framework.security.service;

import com.dermai.project.system.domain.SysRole;
import com.dermai.project.system.domain.SysUser;
import com.dermai.project.system.service.ISysMenuService;
import com.dermai.project.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Shaobo
 */
@Component
public class SysPermissionService {

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private ISysRoleService roleService;

    /**
     * 获取菜单数据权限
     *
     * @param user 用户信息
     * @return 菜单权限信息
     */
    public Set<String> getMenuPermission(SysUser user)
    {
        Set<String> perms = new HashSet<>();
        // 管理员拥有所有权限
        if (user.isAdmin()) {
            perms.add("*:*:*");
        }
        else {
            List<SysRole> roles = user.getRoles();
            if (!CollectionUtils.isEmpty(roles)) {
                // 多角色设置permissions属性，以便数据权限匹配权限
                for (SysRole role : roles) {
                    Set<String> rolePerms = menuService.selectMenuPermsByRoleId(role.getRoleId());
                    role.setPermissions(rolePerms);
                    perms.addAll(rolePerms);
                }
            }
            else {
                perms.addAll(menuService.selectMenuPermsByUserId(user.getUserId()));
            }
        }
        return perms;
    }

    public Set<String> getRolePermission(SysUser user)
    {
        Set<String> roles = new HashSet<>();
        // 管理员拥有所有权限
        if (user.isAdmin()) {
            roles.add("admin");
        } else {
            roles.addAll(roleService.selectRolePermissionByUserId(user.getUserId()));
        }
        return roles;
    }
}
