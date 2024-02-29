package com.otp.ticketservice.ticket.exceptions;

public class CanNotReserveSeatForEventInPastException extends RuntimeException {
    private final int ERROR_CODE = 20011;
    public CanNotReserveSeatForEventInPastException() {
        super("Olyan eseményre ami már elkezdődött nem lehet jegyet eladni!");
    }

    public int getErrorCode() {
        return ERROR_CODE;
    }
}
