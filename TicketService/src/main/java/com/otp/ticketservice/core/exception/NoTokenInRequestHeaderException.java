package com.otp.ticketservice.core.exception;

public class NoTokenInRequestHeaderException extends RuntimeException {
    private final int ERROR_CODE = 10050;
    public NoTokenInRequestHeaderException() {
        super("A felhasználói token nem szerepel!");
    }

    public int getErrorCode() {
        return ERROR_CODE;
    }
}
