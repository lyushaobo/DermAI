package com.dermai.project.system.controller;

import com.dermai.common.constants.Constants;
import com.dermai.framework.security.LoginBody;
//import com.dermai.framework.security.service.SysLoginService;
import com.dermai.framework.security.service.SysLoginService;
import com.dermai.framework.web.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shaobo
 */
@Api(tags = "user login")
@RestController
public class SysLoginController {

    @Autowired
    private SysLoginService loginService;

    @ApiOperation("/login")
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody) {
        AjaxResult ajax = AjaxResult.success();
        // create token
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }
}
