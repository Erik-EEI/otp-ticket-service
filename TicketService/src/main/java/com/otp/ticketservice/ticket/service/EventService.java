package com.otp.ticketservice.ticket.service;

import com.otp.ticketservice.api.dto.EventDataDTO;
import com.otp.ticketservice.ticket.dao.EventDAO;
import com.otp.ticketservice.ticket.dto.event_list.EventDTO;
import com.otp.ticketservice.ticket.dto.event_list.EventListResponseDTO;
import com.otp.ticketservice.ticket.dto.single_event_with_seats.EventWithSeatsResponseDTO;
import com.otp.ticketservice.ticket.interfaces.EventServiceInterface;
import com.otp.ticketservice.ticket.utils.HttpRequestUtil;
import com.otp.ticketservice.ticket.utils.UrlBuilder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EventService implements EventServiceInterface {

    private final EventDAO eventDAO;

    public EventService(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    @Override
    public EventListResponseDTO getAllEvents(){
        return eventDAO.getAllEvents();
    }
    @Override
    public EventWithSeatsResponseDTO getEvent(EventDataDTO eventData){
        return eventDAO.getEvent(eventData);
    }

    @Override
    public EventDTO getEventDetails(Long eventId){
        return eventDAO.getEventDetails(eventId);
    }
}

