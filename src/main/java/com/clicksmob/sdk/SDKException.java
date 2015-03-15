package com.clicksmob.sdk;

/**
 * Base exception type for all SDK-related exceptions.
 *
 * @author Guy Raz Nir
 * @since 13/03/2015
 */
public class SDKException extends RuntimeException {

    private int httpStatus = IRRELEVANT_STATUS;

    /**
     * A value indicating that {@link #getHttpStatus() HTTP status code} is irrelevant to the case.
     */
    public static final int IRRELEVANT_STATUS = -1;

    public SDKException() {
    }

    public SDKException(String message) {
        super(message);
    }

    public SDKException(int httpStatus) {
        this(null, httpStatus);
    }

    public SDKException(String message, int httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public SDKException(String message, Throwable cause) {
        super(message, cause);
    }

    public SDKException(Throwable cause) {
        super(cause);
    }

    public SDKException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public int getHttpStatus() {
        return httpStatus;
    }
}
