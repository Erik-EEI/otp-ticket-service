package com.otp.ticketservice.ticket.dto;

public record PaymentRequestDTO (
        Long eventId,
        Long seatId,
        String cardId
){
}
