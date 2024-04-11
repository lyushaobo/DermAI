package com.dermai.framework.aspectj;

import cn.hutool.core.util.StrUtil;
import com.dermai.common.Utils.SecurityUtils;
import com.dermai.framework.aspectj.annotation.DataScope;
import com.dermai.framework.security.LoginUser;
import com.dermai.framework.security.context.PermissionContextHolder;
import com.dermai.project.system.domain.SysRole;
import com.dermai.project.system.domain.SysUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shaobo
 */
@Aspect
@Component
public class DataScopeAspect {
    /**
     * 全部数据权限
     */
    public static final String DATA_SCOPE_ALL = "1";

    /**
     * 仅本人数据权限
     */
    public static final String DATA_SCOPE_SELF = "2";

    /**
     * 数据权限过滤关键字
     */
    public static final String DATA_SCOPE = "dataScope";

    @Pointcut("@annotation(com.dermai.framework.aspectj.annotation.DataScope)")
    public void dataScopePointCut(){}

//    @Before("dataScopePointCut()")
//    public void doBefore (JoinPoint point) {
//        MethodSignature signature = (MethodSignature)point.getSignature();
//        clearDataScope(point);
//        handleDataScope(point, signature.getMethod().getAnnotation(DataScope.class));
//    }
//
//    private void handleDataScope(JoinPoint point, DataScope dataScope) {
//        // 获取当前的用户
//        LoginUser loginUser = SecurityUtils.getLoginUser();
//        if (loginUser != null) {
//            SysUser currentUser = loginUser.getUser();
//            // 如果是超级管理员，则不过滤数据
//            if (currentUser != null && !currentUser.isAdmin()) {
//                String permission = PermissionContextHolder.getContext();
//                dataScopeFilter(point, currentUser, dataScope.value(), permission);
//            }
//        }
//    }
//
//    private void dataScopeFilter(JoinPoint point, SysUser currentUser, String value, String permission) {
//        StringBuilder sqlString = new StringBuilder();
//        List<String> conditions = new ArrayList<>();
//
//        for (SysRole role : currentUser.getRoles()) {
//            String dataScope = role.getDataScope();
//            if (DATA_SCOPE_ALL.equals(dataScope)) {
//                sqlString = new StringBuilder();
//                conditions.add(dataScope);
//                break;
//            } else if (DATA_SCOPE_SELF.equals(dataScope)) {
//                if (StrUtil.isNotBlank(value)) {
//                    sqlString.append(StrUtil.format(" OR {}.user_id = {} ", currentUser.getUserId()));
//                } else {
//                    // 数据权限为仅本人且没有userAlias别名不查询任何数据
//                    sqlString.append(StrUtil.format(" OR {}.dept_id = 0 ", value));
//                }
//            }
//        }
//    }
}
