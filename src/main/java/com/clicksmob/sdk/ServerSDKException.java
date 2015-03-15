package com.clicksmob.sdk;

/**
 * This exception indicates a failure on the server side, typically resulting from HTTP 5XX family codes (e.g.: HTTP/500 - Internal server error).
 *
 * @author Guy Raz Nir
 * @since 13/03/2015
 */
public class ServerSDKException extends SDKException {

    private final int statusCode;

    public ServerSDKException() {
        this(0);
    }

    public ServerSDKException(int statusCode) {
        this.statusCode = statusCode;
    }

    public ServerSDKException(String message) {
        super(message);
        this.statusCode = 0;
    }

    public ServerSDKException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
