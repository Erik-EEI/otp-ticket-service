package com.otp.ticketservice.ticket.exceptions;

public class SeatDoesNotExistException extends RuntimeException {
    private final int ERROR_CODE = 20002;
    public SeatDoesNotExistException() {
        super("Nem létezik ilyen szék!");
    }

    public int getErrorCode() {
        return ERROR_CODE;
    }
}
