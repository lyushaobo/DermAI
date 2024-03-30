package com.dermai.framework.security;

import lombok.Data;

/**
 * Login Body
 *
 * @author Shaobo
 */
@Data
public class LoginBody {
    /**
     * Username
     */
    private String username;

    /**
     * User Password
     */
    private String password;

    /**
     * Captcha Code
     */
    private String code;

    /**
     * Redis uuid
     */
    private String uuid;
}
