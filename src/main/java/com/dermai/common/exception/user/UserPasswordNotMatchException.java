package com.dermai.common.exception.user;

/**
 * @author Shaobo
 */
public class UserPasswordNotMatchException extends UserException{
    public UserPasswordNotMatchException() {
        super("user.password.not.match", null);
    }
}
