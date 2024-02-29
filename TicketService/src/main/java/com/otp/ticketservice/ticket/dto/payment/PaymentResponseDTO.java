package com.otp.ticketservice.ticket.dto.payment;

public record PaymentResponseDTO(
        Long reservationId,
        boolean success
) {
}
