package com.otp.ticketservice.ticket.exceptions;

public class CanNotReserveOccupiedSeatException extends RuntimeException {
    private final int ERROR_CODE = 20010;
    public CanNotReserveOccupiedSeatException() {
        super("Már lefoglalt székre nem lehet jegyet eladni!");
    }

    public int getErrorCode() {
        return ERROR_CODE;
    }
}
