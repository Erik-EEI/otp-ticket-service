package com.otp.partner.dto;

public record SeatDTO(
        String id,
        int price,
        String currency,
        boolean reserved
) { }
