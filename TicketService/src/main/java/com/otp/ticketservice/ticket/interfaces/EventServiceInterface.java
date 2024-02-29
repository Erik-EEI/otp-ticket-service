package com.otp.ticketservice.ticket.interfaces;

import com.otp.ticketservice.api.dto.EventDataDTO;
import com.otp.ticketservice.ticket.dto.event_list.EventDTO;
import com.otp.ticketservice.ticket.dto.event_list.EventListDTO;
import com.otp.ticketservice.ticket.dto.single_event_with_seats.EventSeatsDTO;

public interface EventServiceInterface {
    EventListDTO getAllEvents();
    EventSeatsDTO getEvent(EventDataDTO eventData);
    EventDTO getEventDetails(Long eventId);
}
