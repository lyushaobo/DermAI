package com.dermai.framework.security.service;

import cn.hutool.core.util.StrUtil;
import com.dermai.common.enums.UserStatus;
import com.dermai.common.exception.ServiceException;
import com.dermai.framework.security.LoginUser;
import com.dermai.project.system.domain.SysUser;
import com.dermai.project.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author Shaobo
 */
@Component
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ISysUserService userService;

    @Autowired
    private SysPasswordService passwordService;

    @Autowired
    private SysPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userService.selectUserByUserName(username);
        if (user == null) {
            log.info("Login user: {} does not exist.", username);
            throw new ServiceException("user.not.exists");
        } else if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            log.info("Login user：{} has been deleted.", username);
            throw new ServiceException("user.password.delete");
        } else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            log.info("Login user：{} has been disabled.", username);
            throw new ServiceException("user.blocked");
        }

        passwordService.validate(user);

        return createLoginUser(user);
    }

    private UserDetails createLoginUser(SysUser user) {
        return new LoginUser(user.getUserId(), user.getDeptId(), user, permissionService.getMenuPermission(user));
    }
}
