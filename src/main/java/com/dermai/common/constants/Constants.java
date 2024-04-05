package com.dermai.common.constants;

/**
 * Common Constants
 *
 * @author Shaobo
 */
public class Constants {

    /**
     * Jwt Token
     */
    public static final String TOKEN = "token";

    /**
     * jwt header
     */
    public static final String LOGIN_USER_KEY = "login_user_key";

    /**
     * Token Prefix
     */
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String[] JSON_WHITELIST_STR
            = { "org.springframework", "com.dermai" };
    public static final String HTTP = "http://";
    public static final String HTTPS = "https://";
    public static final String WWW = "www.";
}
