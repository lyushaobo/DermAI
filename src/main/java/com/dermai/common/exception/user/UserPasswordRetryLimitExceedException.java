package com.dermai.common.exception.user;

/**
 * @author Shaobo
 */
public class UserPasswordRetryLimitExceedException extends UserException {
    public UserPasswordRetryLimitExceedException(int maxRetryCount, int lockTime) {
        super("user.password.retry.limit.exceed", new Object[] { maxRetryCount, lockTime });
    }
}
