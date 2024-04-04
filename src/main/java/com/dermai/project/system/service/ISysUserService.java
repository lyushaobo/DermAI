package com.dermai.project.system.service;

import com.dermai.project.system.domain.SysUser;

/**
 * Syetem User Service
 *
 * @author Shaobo
 */
public interface ISysUserService {

    int updateUserProfile(SysUser sysUser);

    SysUser selectUserByUserName(String username);
}
