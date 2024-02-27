package com.otp.partner.dto;

import jakarta.persistence.Entity;

public record SeatDTO(
        long id,
        String seatName,
        int price,
        String currency,
        boolean reserved
) { }
