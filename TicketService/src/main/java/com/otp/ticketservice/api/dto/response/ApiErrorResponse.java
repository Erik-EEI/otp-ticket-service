package com.otp.ticketservice.api.dto.response;

/**
 * Represents the structure of an API error response.
 */
public record ApiErrorResponse(
        String message,
        int errorCode,
        boolean success
){}
