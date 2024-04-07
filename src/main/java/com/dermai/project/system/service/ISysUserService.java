package com.dermai.project.system.service;

import com.dermai.project.system.domain.SysUser;
import com.github.pagehelper.Page;

/**
 * Syetem User Service
 *
 * @author Shaobo
 */
public interface ISysUserService {

    int updateUserProfile(SysUser sysUser);

    SysUser selectUserByUserName(String username);

    Page<SysUser> selectUserList(SysUser user);
}
