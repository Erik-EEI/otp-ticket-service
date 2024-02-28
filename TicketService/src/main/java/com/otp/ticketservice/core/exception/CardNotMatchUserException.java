package com.otp.ticketservice.core.exception;

public class CardNotMatchUserException extends RuntimeException {
    private final int ERROR_CODE = 10100;
    public CardNotMatchUserException() {
        super("Ez a bankkártya nem ehhez a felhasználóhoz tartozik");
    }

    public int getErrorCode() {
        return ERROR_CODE;
    }
}
