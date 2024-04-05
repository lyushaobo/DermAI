package com.dermai.project.system.controller;

import com.dermai.common.Utils.SecurityUtils;
import com.dermai.common.constants.Constants;
import com.dermai.framework.security.LoginBody;
import com.dermai.framework.security.service.SysLoginService;
import com.dermai.framework.security.service.SysPermissionService;
import com.dermai.framework.web.domain.AjaxResult;
import com.dermai.project.system.domain.SysMenu;
import com.dermai.project.system.domain.SysUser;
import com.dermai.project.system.service.ISysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * @author Shaobo
 */
@Api(tags = "user login")
@RestController
public class SysLoginController {

    @Autowired
    private SysLoginService loginService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private ISysMenuService menuService;

    @ApiOperation("login request")
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody) {
        AjaxResult ajax = AjaxResult.success();
        // create token
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    @ApiOperation("get user info")
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * Get Routers
     *
     * @return Router Info
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters() {
        Long userId = SecurityUtils.getUserId();
        // get parent0: children, parent1: children... list
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
         return AjaxResult.success(menuService.buildMenus(menus));
    }
}
