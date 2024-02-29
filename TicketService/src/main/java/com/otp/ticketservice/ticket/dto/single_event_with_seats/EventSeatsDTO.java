package com.otp.ticketservice.ticket.dto.single_event_with_seats;

import lombok.Data;

import java.util.List;

@Data
public class EventSeatsDTO {
    private long eventId;
    private List<SeatDTO> seats;
}
