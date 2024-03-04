package com.otp.ticketservice.ticket.dto.payment;

/**
 * Data transfer object (DTO) representing a collection of every
 * required parameters to process a payment.
 */
public record PaymentRequestDTO (
        Long eventId,
        Long seatId,
        String cardId
){
}
