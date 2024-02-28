package com.otp.ticketservice.ticket.dto.single_event_with_seats;
import lombok.Data;

@Data
public class EventWithSeatsResponseDTO {
    private EventDataWithSeatsDTO data;
    private boolean success;
}

