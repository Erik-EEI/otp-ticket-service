package com.otp.ticketservice.ticket.dto.event_list;

import java.util.List;

import lombok.Data;

@Data
public class EventListResponseDTO { //TODO Convert to record
    private List<EventDTO> data;
    private boolean success;
}
