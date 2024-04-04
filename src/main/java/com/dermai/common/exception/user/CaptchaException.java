package com.dermai.common.exception.user;

import com.dermai.common.exception.base.BaseException;

/**
 * @author Shaobo
 */
public class CaptchaException extends UserException {
    private static final long serialVersionUID = 1L;
    public CaptchaException() {
        super("user.jcaptcha.error", null);
    }
}
