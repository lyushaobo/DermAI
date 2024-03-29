package com.dermai.framework.web.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

import com.dermai.common.constants.HttpStatus;

/**
 * Ajax results
 *
 * @author Shaobo
 */
public class AjaxResult extends HashMap<String, Object> implements Serializable {

    private static final long serialVersionUID = 1L;

    /** status code */
    public static final String CODE_TAG = "code";

    /** message */
    public static final String MSG_TAG = "msg";

    /** data object */
    public static final String DATA_TAG = "data";

    /**
     * Initializes a newly created AjaxResult object to represent an empty message.
     */
    public AjaxResult() {
    }

    /**
     * Initializes a newly created AjaxResult object
     *
     * @param code status code
     * @param msg message
     */
    public AjaxResult(int code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    /**
     * Initializes a newly created AjaxResult object
     *
     * @param code status code
     * @param msg message
     * @param data data object
     */
    public AjaxResult(int code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (data != null)
        {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * Return success msg
     *
     * @return success
     */
    public static AjaxResult success() {
        return AjaxResult.success("success");
    }

    /**
     * Return success msg
     *
     * @param msg return message
     * @return success
     */
    public static AjaxResult success(String msg) {
        return AjaxResult.success(msg, null);
    }
    /**
     * Return success msg
     *
     * @param msg return message
     * @param data return data object
     * @return success
     */
    public static AjaxResult success(String msg, Object data) {
        return new AjaxResult(HttpStatus.SUCCESS, msg, data);
    }

    /**
     * Return warning
     *
     * @param msg message
     * @return warning
     */
    public static AjaxResult warn(String msg) {
        return AjaxResult.warn(msg, null);
    }

    /**
     * Return warning
     *
     * @param msg message
     * @param data data object
     * @return warning
     */
    public static AjaxResult warn(String msg, Object data) {
        return new AjaxResult(HttpStatus.WARN, msg, data);
    }

    /**
     * Return error
     *
     * @return error
     */
    public static AjaxResult error() {
        return AjaxResult.error("operation failed");
    }

    /**
     * Return error
     *
     * @param msg error message
     * @return error
     */
    public static AjaxResult error(String msg) {
        return AjaxResult.error(msg, null);
    }

    /**
     * Return error
     *
     * @param msg error message
     * @param data data object
     * @return error
     */
    public static AjaxResult error(String msg, Object data) {
        return new AjaxResult(HttpStatus.ERROR, msg, data);
    }

    /**
     * Return error
     *
     * @param code error code
     * @param msg error message
     * @return error
     */
    public static AjaxResult error(int code, String msg) {
        return new AjaxResult(code, msg, null);
    }

    /**
     * If result is success
     *
     * @return result
     */
    public boolean isSuccess() {
        return Objects.equals(HttpStatus.SUCCESS, this.get(CODE_TAG));
    }

    /**
     * If result is warning
     *
     * @return result
     */
    public boolean isWarn() {
        return Objects.equals(HttpStatus.WARN, this.get(CODE_TAG));
    }

    /**
     * If result is error
     *
     * @return result
     */
    public boolean isError() {
        return Objects.equals(HttpStatus.ERROR, this.get(CODE_TAG));
    }

    /**
     * Convenient Chaining Calls
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return AjaxResult
     */
    @Override
    public AjaxResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
