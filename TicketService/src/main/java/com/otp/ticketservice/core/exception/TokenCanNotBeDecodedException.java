package com.otp.ticketservice.core.exception;

public class TokenCanNotBeDecodedException extends RuntimeException {
    private final int ERROR_CODE = 10051;
    public TokenCanNotBeDecodedException() {
        super("A felhasználói token lejárt vagy nem értelmezhető");
    }

    public int getErrorCode() {
        return ERROR_CODE;
    }
}
