package com.otp.ticketservice.ticket.exceptions;

public class UnexpectedResponseFromPartnerException extends RuntimeException {
    private final int ERROR_CODE = 20055;
    public UnexpectedResponseFromPartnerException() {
        super("Nem várt válasz a partner modultól!");
    }

    public int getErrorCode() {
        return ERROR_CODE;
    }
}
