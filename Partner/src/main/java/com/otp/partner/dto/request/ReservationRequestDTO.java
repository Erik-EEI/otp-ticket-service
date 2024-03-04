package com.otp.partner.dto.request;

/**
 * Data Transfer Object (DTO) representing a reservation request.
 */
public record ReservationRequestDTO(
        Long eventId,
        Long seatId
) {
}
