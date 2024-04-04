package com.dermai.common.constants;

/**
 * Redis Constants
 *
 * @author Shaobo
 */
public class RedisConstants {

    /**
     * Captcha redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * Captcha validity period (minutes)
     */
    public static final Integer CAPTCHA_EXPIRATION = 2;
    /**
     * login user token
     */
    public static final String LOGIN_USER_KEY = "login_tokens:";
    /**
     * password error count
     */
    public static final String PWD_ERR_CNT_KEY = "pwd_err_cnt:";
}
