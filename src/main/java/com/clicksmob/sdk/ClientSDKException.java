package com.clicksmob.sdk;

/**
 * An exception representing a client-side error. This exception is typically generated when server produce HTTP error 4XX (for example: HTTP/400 - Bad request, due to invalid parameters).
 *
 * @author Guy Raz Nir
 * @since 13/03/2015
 */
public class ClientSDKException extends SDKException {

    private final int statusCode;

    public ClientSDKException() {
        this(0);
    }

    public ClientSDKException(int statusCode) {
        this.statusCode = statusCode;
    }

    public ClientSDKException(String message) {
        super(message);
        this.statusCode = 0;
    }

    public ClientSDKException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
