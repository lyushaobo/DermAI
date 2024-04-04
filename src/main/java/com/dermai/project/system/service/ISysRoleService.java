package com.dermai.project.system.service;

import java.util.Set;

/**
 * @author Shaobo
 */
public interface ISysRoleService {
    Set<String> selectRolePermissionByUserId(Long userId);
}
