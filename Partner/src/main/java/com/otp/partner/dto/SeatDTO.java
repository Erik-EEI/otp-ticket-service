package com.otp.partner.dto;

/**
 * Data Transfer Object (DTO) representing a seat.
 */
public record SeatDTO(
        long id,
        String seatName,
        int price,
        String currency,
        boolean reserved
) {}
