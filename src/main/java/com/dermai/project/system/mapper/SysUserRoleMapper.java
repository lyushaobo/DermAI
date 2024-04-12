package com.dermai.project.system.mapper;

import com.dermai.project.system.domain.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Shaobo
 */
@Mapper
public interface SysUserRoleMapper {
    int batchUserRole(List<SysUserRole> list);

    void deleteUserRoleByUserId(Long userId);

    void deleteUserRole(Long[] userIds);
}
