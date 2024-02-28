package com.otp.ticketservice.core.exception;

public class NotEnoughAmountOnCardException extends RuntimeException {
    private final int ERROR_CODE = 10101;
    public NotEnoughAmountOnCardException() {
        super("A felhasználónak nincs elegendő pénze hogy megvásárolja a jegyet!");
    }

    public int getErrorCode() {
        return ERROR_CODE;
    }
}
