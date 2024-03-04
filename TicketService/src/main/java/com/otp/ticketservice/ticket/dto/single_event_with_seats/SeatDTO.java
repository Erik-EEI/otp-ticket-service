package com.otp.ticketservice.ticket.dto.single_event_with_seats;

/**
 * Data transfer object (DTO) representing a seat in an event.
 */
public record SeatDTO(
        long id,
        String seatName,
        int price,
        String currency,
        boolean reserved
){}
