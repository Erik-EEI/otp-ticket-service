package com.otp.partner.exception;

public class ReservationNotFound extends RuntimeException{
    private final int ERROR_CODE = 90005;
    public ReservationNotFound() {
        super("Nem létezik ilyen foglalás!");
    }

    public int getErrorCode() {
        return ERROR_CODE;
    }
}
