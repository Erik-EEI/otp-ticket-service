package com.otp.ticketservice.ticket.dto.event_list;

import java.util.List;

import lombok.Data;

@Data
public class EventListDTO { //TODO Convert to record
    private List<EventDTO> data;
    private boolean success;
}
