package com.dermai.project.system.service.impl;

import com.dermai.common.constants.UserConstants;
import com.dermai.common.exception.ServiceException;
import com.dermai.project.system.domain.SysUser;
import com.dermai.project.system.domain.SysUserRole;
import com.dermai.project.system.mapper.SysUserMapper;
import com.dermai.project.system.mapper.SysUserRoleMapper;
import com.dermai.project.system.service.ISysUserService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shaobo
 */
@Service
public class SysUserServiceImpl implements ISysUserService {
    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Override
    public int updateUserProfile(SysUser sysUser) {
        return userMapper.updateUser(sysUser);
    }

    @Override
    public SysUser selectUserByUserName(String userName) {
        return userMapper.selectUserByUserName(userName);
    }

    @Override
    public List<SysUser> selectUserList(SysUser user) {
        return userMapper.selectUserList(user);
    }

    @Override
    public SysUser selectUserById(Long userId) {
        return userMapper.selectUserById(userId);
    }

    @Override
    public void checkUserAllowed(SysUser user) {
        if (user.isAdmin() && user.getUserId() != null) {
            throw new ServiceException("can not edit admin");
        }
    }

    @Override
    public boolean checkUserNameUnique(SysUser user) {
        long userId = user.getUserId() == null ? -1L : user.getUserId();
        SysUser info = userMapper.checkUserNameUnique(user.getUserName());
        if (info != null && info.getUserId() != userId) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public boolean checkPhoneUnique(SysUser user) {
        long userId = user.getUserId() == null ? -1L : user.getUserId();
        SysUser info = userMapper.checkPhoneUnique(user.getPhonenumber());
        if (info != null && info.getUserId() != userId) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public boolean checkEmailUnique(SysUser user) {
        long userId = user.getUserId() == null ? -1L : user.getUserId();
        SysUser info = userMapper.checkEmailUnique(user.getEmail());
        if (info != null && info.getUserId() != userId) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    @Transactional
    public int updateUser(SysUser user) {
        Long userId = user.getUserId();
        // delete user and role
        userRoleMapper.deleteUserRoleByUserId(userId);
        // insert user and role
        insertUserRole(user);
        return userMapper.updateUser(user);
    }

    /**
     * add new user
     * @param user new user
     * @return number of rows affected
     */
    @Override
    @Transactional
    public int insertUser(SysUser user) {
        int rows = userMapper.insertUser(user);
        insertUserRole(user);
        return rows;
    }

    @Override
    @Transactional
    public int deleteUserByIds(Long[] userIds) {
        for (Long userId : userIds) {
            checkUserAllowed(new SysUser(userId));
        }
        userRoleMapper.deleteUserRole(userIds);
        return userMapper.deleteUserByIds(userIds);
    }

    @Override
    public int resetPwd(SysUser user) {
        return userMapper.updateUser(user);
    }

    public void insertUserRole(SysUser user) {
        this.insertUserRole(user.getUserId(), user.getRoleIds());
    }

    public void insertUserRole(Long userId, Long[] roleIds)
    {
        if (roleIds != null && roleIds.length > 0) {
            // insert user and role
            List<SysUserRole> list = new ArrayList<>(roleIds.length);
            for (Long roleId : roleIds) {
                SysUserRole ur = new SysUserRole();
                ur.setUserId(userId);
                ur.setRoleId(roleId);
                list.add(ur);
            }
            userRoleMapper.batchUserRole(list);
        }
    }
}
