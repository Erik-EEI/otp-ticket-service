package com.otp.ticketservice.core.exception;

public class TokenNotFoundException extends RuntimeException {
    private final int ERROR_CODE = 10053;
    public TokenNotFoundException() {
        super("Érvénytelen token!");
    }

    public int getErrorCode() {
        return ERROR_CODE;
    }
}
