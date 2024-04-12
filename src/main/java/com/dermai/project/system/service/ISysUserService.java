package com.dermai.project.system.service;

import com.dermai.project.system.domain.SysUser;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * Syetem User Service
 *
 * @author Shaobo
 */
public interface ISysUserService {

    int updateUserProfile(SysUser sysUser);

    SysUser selectUserByUserName(String username);

    List<SysUser> selectUserList(SysUser user);

    SysUser selectUserById(Long userId);

    void checkUserAllowed(SysUser user);

    boolean checkUserNameUnique(SysUser user);

    boolean checkPhoneUnique(SysUser user);

    boolean checkEmailUnique(SysUser user);

    int updateUser(SysUser user);

    /**
     * add new user
     * @param user new user
     * @return Number of rows affected
     */
    int insertUser(SysUser user);
}
