package com.dermai.project.system.service;

import com.dermai.project.system.domain.SysRole;

import java.util.List;
import java.util.Set;

/**
 * @author Shaobo
 */
public interface ISysRoleService {
    Set<String> selectRolePermissionByUserId(Long userId);

    List<SysRole> selectRoleAll();

    List<SysRole> selectRoleList(SysRole role);
}
