package com.otp.ticketservice.ticket.exceptions;

public class CanNotReserveSetForEventInPastException extends RuntimeException {
    private final int ERROR_CODE = 20011;
    public CanNotReserveSetForEventInPastException() {
        super("Olyan eseményre ami már elkezdődött nem lehet jegyet eladni!");
    }

    public int getErrorCode() {
        return ERROR_CODE;
    }
}
