package com.dermai.common.Utils;

import com.dermai.common.constants.HttpStatus;
import com.dermai.common.exception.ServiceException;
import com.dermai.framework.security.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Shaobo
 */
@Component
public class SecurityUtils {
    /**
     * Get Authentication
     */
    public static Authentication getAuthentication()
    {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * whether the password is correct
     *
     * @param rawPassword raw password
     * @param password inputed password
     * @return whether the password is correct
     */
    public static boolean matchesPassword(String rawPassword, String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, password);
    }

    public static LoginUser getLoginUser() {
        try {
            return (LoginUser) getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new ServiceException("Get User Information Exception", HttpStatus.UNAUTHORIZED);
        }
    }
}
