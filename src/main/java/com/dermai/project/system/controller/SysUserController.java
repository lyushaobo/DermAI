package com.dermai.project.system.controller;

import com.dermai.common.Utils.SecurityUtils;
import com.dermai.framework.web.controller.BaseController;
import com.dermai.framework.web.domain.AjaxResult;
import com.dermai.project.system.domain.SysRole;
import com.dermai.project.system.domain.SysUser;
import com.dermai.project.system.service.ISysRoleService;
import com.dermai.project.system.service.ISysUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Shaobo
 */
@Api(tags = "user controller")
@RestController
@RequestMapping("/system/user")
public class SysUserController extends BaseController {

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysRoleService roleService;

    @ApiOperation("get user list")
    @GetMapping("/list")
    @PreAuthorize("@ss.hasPermi('system:user:list')")
    public AjaxResult list(SysUser user) {
        startPage();
        List<SysUser> userList = userService.selectUserList(user);
        PageInfo<SysUser> sysUserPageInfo = new PageInfo<>(userList);
        AjaxResult ajaxResult = AjaxResult.success();
        ajaxResult.put("rows", sysUserPageInfo.getList());
        ajaxResult.put("total", sysUserPageInfo.getTotal());
        return ajaxResult;
    }

    @ApiOperation("Get user info by Id")
    @GetMapping(value = {"/{userId}", "/"})
    @PreAuthorize("@ss.hasPermi('system:user:query')")
    public AjaxResult getInfo(@PathVariable(value = "userId", required = false) Long userId) {
        AjaxResult ajaxResult = AjaxResult.success();
        List<SysRole> roles = roleService.selectRoleAll();
        ajaxResult.put("roles", SysUser.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        if (userId != null) {
            SysUser user = userService.selectUserById(userId);
            ajaxResult.put(AjaxResult.DATA_TAG, user);
            ajaxResult.put("roleIds", user.getRoles().stream().map(SysRole::getRoleId).collect(Collectors.toList()));
        }
        return ajaxResult;
    }

    @ApiOperation("Update user info")
    @PutMapping
    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    public AjaxResult edit(@RequestBody SysUser user) {
        userService.checkUserAllowed(user);
        if (!userService.checkUserNameUnique(user)) {
            return error("Edit user'" + user.getUserName() + "'failed, username already exists");
        }
        else if (user.getPhonenumber() != null && !userService.checkPhoneUnique(user)) {
            return error("Edit user'" + user.getUserName() + "'failed, phonenumber already exists");
        }
        else if (user.getEmail() != null && !userService.checkEmailUnique(user)) {
            return error("Edit user'" + user.getUserName() + "'failed, email already exists");
        }
        user.setUpdateBy(user.getUserName());

        int rows = userService.updateUser(user);

        if (rows == 0) {
            return AjaxResult.error();
        }

        return AjaxResult.success();
    }

    @PreAuthorize("@ss.hasPermi('system:user:add')")
    @ApiOperation("add new user")
    @PostMapping
    public AjaxResult add(@RequestBody SysUser user) {
        userService.checkUserAllowed(user);
        if (!userService.checkUserNameUnique(user)) {
            return error("Edit user'" + user.getUserName() + "'failed, username already exists");
        }
        else if (user.getPhonenumber() != null && !userService.checkPhoneUnique(user)) {
            return error("Edit user'" + user.getUserName() + "'failed, phonenumber already exists");
        }
        else if (user.getEmail() != null && !userService.checkEmailUnique(user)) {
            return error("Edit user'" + user.getUserName() + "'failed, email already exists");
        }
        user.setCreateBy(SecurityUtils.getLoginUser().getUsername());
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        int rows = userService.insertUser(user);
        if (rows == 0) {
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }

}
