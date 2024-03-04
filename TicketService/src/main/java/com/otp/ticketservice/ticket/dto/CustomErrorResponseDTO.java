package com.otp.ticketservice.ticket.dto;

/**
 * Data transfer object (DTO) representing a custom error response.
 */
public record CustomErrorResponseDTO (
        String message,
        int errorCode,
        boolean success
){};
