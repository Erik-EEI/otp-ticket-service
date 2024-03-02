package com.otp.ticketservice.core.exception;

public class CardNotFoundException extends RuntimeException {
    private final int ERROR_CODE = 10100;
    public CardNotFoundException() {
        super("Nem található kártya a megadott azonosító alapján");
    }

    public int getErrorCode() {
        return ERROR_CODE;
    }
}
