package com.otp.ticketservice.ticket.exceptions;

public class PartnerServerErrorException extends RuntimeException {
    private final int ERROR_CODE = 20050;
    public PartnerServerErrorException() {
        super("Hiba a rendezvényszolgáltató rendszerében!");
    }

    public int getErrorCode() {
        return ERROR_CODE;
    }
}
