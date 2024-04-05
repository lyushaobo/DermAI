package com.dermai.project.system.mapper;

import com.dermai.project.system.domain.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Shaobo
 */
@Mapper
public interface SysMenuMapper {
    List<String> selectMenuPermsByRoleId(Long roleId);

    List<String> selectMenuPermsByUserId(Long userId);

    List<SysMenu> selectMenuTreeAll();

    List<SysMenu> selectMenuTreeByUserId(Long userId);
}
