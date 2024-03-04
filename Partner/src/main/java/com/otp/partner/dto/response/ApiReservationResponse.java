package com.otp.partner.dto.response;

/**
 * Data Transfer Object (DTO) representing a response for a reservation operation.
 */
public record ApiReservationResponse(
        Long reservationId,
        boolean success
){}
