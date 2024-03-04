package com.otp.ticketservice.api.dto;

/**
 * Represents data required for making a payment for a reservation.
 */
public record PaymentDataDTO(
        Long eventId,
        Long seatId,
        String cardID,
        String userToken
){}
