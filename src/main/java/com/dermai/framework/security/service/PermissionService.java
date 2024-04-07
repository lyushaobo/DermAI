package com.dermai.framework.security.service;

import cn.hutool.core.util.StrUtil;
import com.dermai.common.Utils.SecurityUtils;
import com.dermai.common.constants.Constants;
import com.dermai.framework.security.LoginUser;
import com.dermai.framework.security.context.PermissionContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Set;

/**
 * @author Shaobo
 */
@Service("ss")
public class PermissionService {

    /**
     * has permission
     * @param permission
     * @return
     */
    public boolean hasPermi(String permission) {
        if (StrUtil.isEmpty(permission)) {
            return false;
        }
        LoginUser loginUser = SecurityUtils.getLoginUser();
        if (loginUser == null || CollectionUtils.isEmpty(loginUser.getPermissions())) {
            return false;
        }
        PermissionContextHolder.setContext(permission);
        return hasPermissions(loginUser.getPermissions(), permission);
    }

    private boolean hasPermissions(Set<String> permissions, String permission) {
        return permissions.contains(Constants.ALL_PERMISSION) || permissions.contains(StrUtil.trim(permission));
    }
}
