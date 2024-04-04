package com.dermai.framework.security.service;

import com.dermai.common.Utils.SecurityUtils;
import com.dermai.common.constants.RedisConstants;
import com.dermai.common.exception.user.UserPasswordNotMatchException;
import com.dermai.common.exception.user.UserPasswordRetryLimitExceedException;
import com.dermai.framework.redis.RedisCache;
import com.dermai.framework.security.context.AuthenticationContextHolder;
import com.dermai.project.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * password judgement
 *
 * @author Shaobo
 */
@Component
public class SysPasswordService {

    @Autowired
    private RedisCache redisCache;

    @Value(value = "${user.password.maxRetryCount}")
    private int maxRetryCount;

    @Value(value = "${user.password.lockTime}")
    private int lockTime;

    public void validate(SysUser user) {
        // get username and password
        Authentication usernamePasswordAuthenticationToken = AuthenticationContextHolder.getContext();
        String username = usernamePasswordAuthenticationToken.getName();
        String password = usernamePasswordAuthenticationToken.getCredentials().toString();
        // get retry count
        Integer retryCount = redisCache.getCacheObject(getCacheKey(username));

        if (retryCount == null) {
            retryCount = 0;
        }
        // Maximum number of errors reached
        if (retryCount >= maxRetryCount) {
            throw new UserPasswordRetryLimitExceedException(maxRetryCount, lockTime);
        }
        // insert or update wrong password attempt redis
        if (!matches(user, password)) {
            retryCount = retryCount + 1;
            redisCache.setCacheObject(getCacheKey(username), retryCount, lockTime, TimeUnit.MINUTES);
            throw new UserPasswordNotMatchException();
        }
        // password correct
        else {
            clearLoginRecordCache(username);
        }
    }

    /**
     * get cache key "pwd_err_cnt: username"
     * @param username username
     * @return cache key
     */
    private String getCacheKey(String username) {
        return RedisConstants.PWD_ERR_CNT_KEY + username;
    }

    /**
     * whether the password is correct
     *
     * @param user login user
     * @param rawPassword raw password
     * @return whether the password is correct
     */
    public boolean matches(SysUser user, String rawPassword) {
        return SecurityUtils.matchesPassword(rawPassword, user.getPassword());
    }

    /**
     * password correct, delete cache
     * @param username username
     */
    private void clearLoginRecordCache(String username) {
        if (redisCache.hasKey(getCacheKey(username))) {
            redisCache.deleteObject(getCacheKey(username));
        }
    }
}
