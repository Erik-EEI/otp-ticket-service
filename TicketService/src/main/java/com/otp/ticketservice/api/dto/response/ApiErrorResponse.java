package com.otp.ticketservice.api.dto.response;

public record ApiErrorResponse(
        String message,
        int errorCode,
        boolean success
){}
