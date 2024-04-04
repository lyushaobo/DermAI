package com.dermai.common.exception;

import lombok.Getter;

/**
 * @author Shaobo
 */
public class ServiceException extends RuntimeException{
    /**
     * Error Code
     */
    @Getter
    private Integer code;

    /**
     * Error message
     */
    private String message;

    /**
     * Detailed message
     */
    @Getter
    private String detailMessage;
    public ServiceException() {
    }

    public ServiceException(String message) {
        this.message = message;
    }

    public ServiceException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public ServiceException setMessage(String message) {
        this.message = message;
        return this;
    }

    public ServiceException setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
        return this;
    }
}
