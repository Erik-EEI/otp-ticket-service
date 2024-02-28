package com.otp.ticketservice.ticket.interfaces;

import com.otp.ticketservice.api.dto.EventDataDTO;
import com.otp.ticketservice.ticket.dto.event_list.EventListResponseDTO;
import com.otp.ticketservice.ticket.dto.single_event_with_seats.EventWithSeatsResponseDTO;

public interface EventServiceInterface {
    EventListResponseDTO getAllEvents();
    EventWithSeatsResponseDTO getEvent(EventDataDTO eventData);
}
