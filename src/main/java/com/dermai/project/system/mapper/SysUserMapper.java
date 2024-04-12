package com.dermai.project.system.mapper;

import com.dermai.project.system.domain.SysUser;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Shaobo
 */
@Mapper
public interface SysUserMapper {
    int updateUser(SysUser user);

    SysUser selectUserByUserName(String userName);

    Page<SysUser> selectUserList(SysUser user);

    SysUser selectUserById(Long userId);

    SysUser checkUserNameUnique(String userName);

    SysUser checkPhoneUnique(String phonenumber);

    SysUser checkEmailUnique(String email);

    /**
     * save new user
     * @param user new user
     * @return number of rows affected
     */
    int insertUser(SysUser user);

    int deleteUserByIds(Long[] userIds);
}
