package com.dermai.common.exception.user;

import com.dermai.common.exception.base.BaseException;

/**
 * User Exception
 *
 * @author Shaobo
 */
public class UserException extends BaseException {

    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }
}
