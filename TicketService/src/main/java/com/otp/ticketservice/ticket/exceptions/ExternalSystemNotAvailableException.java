package com.otp.ticketservice.ticket.exceptions;

public class ExternalSystemNotAvailableException extends RuntimeException {
    private final int ERROR_CODE = 20404;
    public ExternalSystemNotAvailableException() {
        super("A külső rendszer nem elérhető!");
    }

    public int getErrorCode() {
        return ERROR_CODE;
    }
}
