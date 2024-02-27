package com.otp.partner.exception;

public class EventNotFoundException extends RuntimeException {
    private final int ERROR_CODE = 90001;
    public EventNotFoundException() {
        super("Nem létezik ilyen esemény!");
    }

    public int getErrorCode() {
        return ERROR_CODE;
    }
}
