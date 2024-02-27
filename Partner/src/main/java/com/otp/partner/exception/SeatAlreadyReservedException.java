package com.otp.partner.exception;

public class SeatAlreadyReservedException extends RuntimeException {
    private final int ERROR_CODE = 90010;
    public SeatAlreadyReservedException() {
        super("Már lefoglalt székre nem lehet jegyet eladni!");
    }

    public int getErrorCode() {
        return ERROR_CODE;
    }
}
