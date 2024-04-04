package com.dermai.common.exception.user;

import com.dermai.common.exception.base.BaseException;

/**
 * @author Shaobo
 */
public class CaptchaExpireException extends UserException {
    public CaptchaExpireException() {
        super("user.jcaptcha.expire", null);
    }
}
