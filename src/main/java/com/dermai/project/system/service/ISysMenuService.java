package com.dermai.project.system.service;

import com.dermai.project.system.domain.SysMenu;
import com.dermai.project.system.domain.vo.RouterVo;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author Shaobo
 */
public interface ISysMenuService {
    Set<String> selectMenuPermsByRoleId(Long roleId);

    Collection<String> selectMenuPermsByUserId(Long userId);

    List<SysMenu> selectMenuTreeByUserId(Long userId);

    List<RouterVo> buildMenus(List<SysMenu> menus);
}
