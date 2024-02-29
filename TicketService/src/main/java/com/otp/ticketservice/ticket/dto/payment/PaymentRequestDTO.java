package com.otp.ticketservice.ticket.dto.payment;

public record PaymentRequestDTO (
        Long eventId,
        Long seatId,
        String cardId
){
}
