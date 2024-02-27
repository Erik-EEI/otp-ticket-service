
package com.otp.partner.exception;
public class SeatNotFoundException extends RuntimeException {
    private final int ERROR_CODE = 90002;
    public SeatNotFoundException() {
        super("Nem létezik ilyen szék!");
    }

    public int getErrorCode() {
        return ERROR_CODE;
    }
}
