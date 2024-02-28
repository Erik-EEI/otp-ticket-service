package com.otp.ticketservice.api.dto;

public record PaymentDataDTO(
        Long eventId,
        Long seatId,
        Long cardID
) {
}
