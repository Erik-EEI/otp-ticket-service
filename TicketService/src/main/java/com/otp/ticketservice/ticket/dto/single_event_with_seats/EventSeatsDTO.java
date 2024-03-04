package com.otp.ticketservice.ticket.dto.single_event_with_seats;

import java.util.List;

/**
 * Data transfer object (DTO) representing an event with its associated seats.
 */
public record EventSeatsDTO(
    long eventId,
    List<SeatDTO> seats
){}
