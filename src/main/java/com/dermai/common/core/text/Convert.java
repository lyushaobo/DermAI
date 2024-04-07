package com.dermai.common.core.text;

import cn.hutool.core.util.StrUtil;

/**
 * Data Type Converter
 *
 * @author Shaobo
 */
public class Convert {
    public static Integer toInt(Object value, Integer defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        if (value instanceof Integer) {
            return (Integer) value;
        }
        if (value instanceof Number) {
            return ((Number) value).intValue();
        }
        final String valueStr = toStr(value, null);
        if (StrUtil.isEmpty(valueStr)) {
            return defaultValue;
        }try {
            return Integer.parseInt(valueStr.trim());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static String toStr(Object value, String defaultValue)
    {
        if (null == value) {
            return defaultValue;
        }
        if (value instanceof String) {
            return (String) value;
        }
        return value.toString();
    }

    public static Boolean toBool(Object value, Boolean defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        if (value instanceof Boolean) {
            return (Boolean) value;
        }
        String valueStr = toStr(value, null);
        if (StrUtil.isEmpty(valueStr)) {
            return defaultValue;
        }
        valueStr = valueStr.trim().toLowerCase();
        switch (valueStr) {
            case "true":
            case "yes":
            case "ok":
            case "1":
                return true;
            case "false":
            case "no":
            case "0":
                return false;
            default:
                return defaultValue;
        }
    }

    public static String toStr(Object value) {
        return toStr(value, null);
    }
    public static Integer toInt(Object value) {
        return toInt(value, null);
    }

    public static Boolean toBool(Object value) {
        return toBool(value, null);
    }
}
