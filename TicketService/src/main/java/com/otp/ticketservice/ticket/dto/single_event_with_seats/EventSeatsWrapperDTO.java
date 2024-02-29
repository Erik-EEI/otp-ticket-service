package com.otp.ticketservice.ticket.dto.single_event_with_seats;

import lombok.Data;

@Data
public class EventSeatsWrapperDTO {
    private EventSeatsDTO data;
    private boolean success;
}
