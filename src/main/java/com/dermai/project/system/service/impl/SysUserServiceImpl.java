package com.dermai.project.system.service.impl;

import com.dermai.project.system.domain.SysUser;
import com.dermai.project.system.mapper.SysUserMapper;
import com.dermai.project.system.service.ISysUserService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Shaobo
 */
@Service
public class SysUserServiceImpl implements ISysUserService {
    @Autowired
    private SysUserMapper userMapper;
    @Override
    public int updateUserProfile(SysUser sysUser) {
        return userMapper.updateUser(sysUser);
    }

    @Override
    public SysUser selectUserByUserName(String userName) {
        return userMapper.selectUserByUserName(userName);
    }

    @Override
    public Page<SysUser> selectUserList(SysUser user) {
        return userMapper.selectUserList(user);
    }
}
