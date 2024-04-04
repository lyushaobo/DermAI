package com.dermai.project.system.service;

import java.util.Collection;
import java.util.Set;

/**
 * @author Shaobo
 */
public interface ISysMenuService {
    Set<String> selectMenuPermsByRoleId(Long roleId);

    Collection<String> selectMenuPermsByUserId(Long userId);
}
