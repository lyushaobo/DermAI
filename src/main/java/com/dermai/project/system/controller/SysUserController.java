package com.dermai.project.system.controller;

import com.dermai.framework.web.controller.BaseController;
import com.dermai.framework.web.domain.AjaxResult;
import com.dermai.project.system.domain.SysUser;
import com.dermai.project.system.service.ISysUserService;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

/**
 * @author Shaobo
 */
@Api(tags = "user controller")
@RestController
@RequestMapping("/system/user")
public class SysUserController extends BaseController {

    @Autowired
    private ISysUserService userService;

    @ApiOperation("get user list")
    @GetMapping("/list")
    public AjaxResult list(SysUser user) {
        startPage();
        Page<SysUser> pageResult = userService.selectUserList(user);
        AjaxResult ajaxResult = AjaxResult.success();
        ajaxResult.put("rows", pageResult.getResult());
        ajaxResult.put("total", pageResult.getTotal());
        return ajaxResult;
    }
}
