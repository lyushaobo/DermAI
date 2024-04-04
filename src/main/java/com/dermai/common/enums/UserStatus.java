package com.dermai.common.enums;

import lombok.Getter;

/**
 * User Status
 *
 * @author Shaobo
 */
@Getter
public enum UserStatus {
    OK("0", "normal"), DISABLE("1", "blocked"), DELETED("2", "deleted");

    private final String code;
    private final String info;

    UserStatus(String code, String info)
    {
        this.code = code;
        this.info = info;
    }

}
