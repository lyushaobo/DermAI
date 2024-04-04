package com.dermai.common.exception.user;

/**
 * @author Shaobo
 */
public class UserNotExistsException extends UserException{
    public UserNotExistsException() {
        super("user.not.exists", null);
    }
}
