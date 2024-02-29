package com.otp.ticketservice.ticket.exceptions;

public class EventDoesNotExistException extends RuntimeException {
    private final int ERROR_CODE = 20001;
    public EventDoesNotExistException() {
        super("Nem létezik ilyen esemény!");
    }

    public int getErrorCode() {
        return ERROR_CODE;
    }
}
