package com.dermai.common.exception.file;

import com.dermai.common.exception.base.BaseException;

/**
 * File Base Excepetion
 *
 * @author Shaobo
 */
public class FileException extends BaseException {

    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args) {
        super("file", code, args, null);
    }
}
