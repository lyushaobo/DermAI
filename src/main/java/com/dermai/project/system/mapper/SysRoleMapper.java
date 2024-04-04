package com.dermai.project.system.mapper;

import com.dermai.project.system.domain.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Shaobo
 */
@Mapper
public interface SysRoleMapper {
    List<SysRole> selectRolePermissionByUserId(Long userId);
}
