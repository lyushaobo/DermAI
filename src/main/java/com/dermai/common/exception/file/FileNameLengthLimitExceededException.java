package com.dermai.common.exception.file;

/**
 * @author Shaobo
 */
public class FileNameLengthLimitExceededException extends FileException {
    public FileNameLengthLimitExceededException(long maxLength) {
        super("upload.filename.exceed.length", new Object[] {maxLength});
    }
}
