package com.otp.partner.exception;

public class ReservationNotFoundException extends RuntimeException{
    private final int ERROR_CODE = 90005;
    public ReservationNotFoundException() {
        super("Nem létezik ilyen foglalás!");
    }

    public int getErrorCode() {
        return ERROR_CODE;
    }
}
