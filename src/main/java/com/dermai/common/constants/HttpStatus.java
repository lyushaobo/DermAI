package com.dermai.common.constants;

/**
 * Http Status Constants
 *
 * @author Shaobo
 */
public class HttpStatus {
    /**
     * Operation Success
     */
    public static final int SUCCESS = 200;

    /**
     * The request has been fulfilled
     */
    public static final int CREATED = 201;

    /**
     * The request has been accepted for processing, but the processing has not been completed.
     */
    public static final int ACCEPTED = 202;

    /**
     * The server successfully processed the request, and is not returning any content.
     */
    public static final int NO_CONTENT = 204;

    /**
     * This and all future requests should be directed to the given URI.
     */
    public static final int MOVED_PERM = 301;

    /**
     * The response to the request can be found under another URI using the GET method.
     */
    public static final int SEE_OTHER = 303;

    /**
     * Indicates that the resource has not been modified since the version specified by the request headers
     * If-Modified-Since or If-None-Match.
     */
    public static final int NOT_MODIFIED = 304;

    /**
     * The server cannot or will not process the request due to an apparent client error.
     */
    public static final int BAD_REQUEST = 400;

    /**
     * UNAUTHORIZED
     */
    public static final int UNAUTHORIZED = 401;

    /**
     * This may be due to the user not having the necessary permissions for a resource or needing an account of
     * some sort, or attempting a prohibited action
     */
    public static final int FORBIDDEN = 403;

    /**
     * The requested resource could not be found but may be available in the future.
     */
    public static final int NOT_FOUND = 404;

    /**
     * A request method is not supported for the requested resource
     */
    public static final int BAD_METHOD = 405;

    /**
     * Indicates that the request could not be processed because of conflict in the current state of the resource.
     */
    public static final int CONFLICT = 409;

    /**
     * Unsupported Media Type
     */
    public static final int UNSUPPORTED_TYPE = 415;

    /**
     * Internal Server Error
     */
    public static final int ERROR = 500;

    /**
     * Interface Not Implemented
     */
    public static final int NOT_IMPLEMENTED = 501;

    /**
     * System Warning
     */
    public static final int WARN = 601;
}
