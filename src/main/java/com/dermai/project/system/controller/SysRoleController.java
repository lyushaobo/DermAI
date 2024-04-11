package com.dermai.project.system.controller;

import com.dermai.framework.web.controller.BaseController;
import com.dermai.framework.web.domain.AjaxResult;
import com.dermai.project.system.domain.SysRole;
import com.dermai.project.system.domain.SysUser;
import com.dermai.project.system.service.ISysRoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Shaobo
 */
@RestController
@RequestMapping("/system/role")
@Api(tags = "role controller")
public class SysRoleController extends BaseController {

    @Autowired
    private ISysRoleService roleService;

    @ApiOperation("get user list")
    @GetMapping("/list")
    @PreAuthorize("@ss.hasPermi('system:role:list')")
    public AjaxResult list(SysRole role) {
        startPage();
        List<SysRole> roleList= roleService.selectRoleList(role);
        PageInfo<SysRole> sysRolePageInfo = new PageInfo<>(roleList);
        AjaxResult ajaxResult = AjaxResult.success();
        ajaxResult.put("rows", sysRolePageInfo.getList());
        ajaxResult.put("total", sysRolePageInfo.getTotal());
        return ajaxResult;
    }

}
