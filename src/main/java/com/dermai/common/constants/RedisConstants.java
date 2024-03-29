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
}
