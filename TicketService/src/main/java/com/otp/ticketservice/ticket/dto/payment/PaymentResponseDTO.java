package com.otp.ticketservice.ticket.dto.payment;

/**
 * Data transfer object (DTO) representing a payment response from the partner module.
 */
public record PaymentResponseDTO(
        Long reservationId,
        boolean success
) {
}
