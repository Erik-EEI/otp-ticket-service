package com.otp.ticketservice.core.exception;

public class TokenNotFoundException extends RuntimeException {
    private final int ERROR_CODE = 10050;
    public TokenNotFoundException() {
        super("A felhasználói token nem szerepel");
    }

    public int getErrorCode() {
        return ERROR_CODE;
    }
}
