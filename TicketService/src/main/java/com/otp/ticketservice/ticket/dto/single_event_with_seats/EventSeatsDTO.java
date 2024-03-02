package com.otp.ticketservice.ticket.dto.single_event_with_seats;

import java.util.List;
public record EventSeatsDTO(
    long eventId,
    List<SeatDTO> seats
){}
