package com.dermai.common.exception.file;

/**
 * @author Shaobo
 */
public class FileSizeLimitExceededException extends FileException{
    public FileSizeLimitExceededException(long maxSize) {
        super("upload.exceed.maxSize", new Object[] {maxSize});
    }
}
