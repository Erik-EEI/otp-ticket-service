package com.otp.ticketservice.ticket.service;

import com.otp.ticketservice.api.dto.EventDataDTO;
import com.otp.ticketservice.ticket.dao.EventDAO;
import com.otp.ticketservice.ticket.dto.detailed_event.DetailedEventDTO;
import com.otp.ticketservice.ticket.dto.event_list.EventListDTO;
import com.otp.ticketservice.ticket.dto.single_event_with_seats.EventSeatsDTO;
import com.otp.ticketservice.ticket.interfaces.EventServiceInterface;
import com.otp.ticketservice.ticket.mapper.EventMapper;
import com.otp.ticketservice.ticket.utils.HttpResponseExceptionHandler;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;

@Service
public class EventService implements EventServiceInterface {

    private final EventDAO eventDAO;

    public EventService(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    @Override
    @Cacheable("all_events")
    public EventListDTO getAllEvents(){
        HttpResponse<String> response = eventDAO.getAllEvents();
        HttpResponseExceptionHandler.checkForException( response );

        return EventMapper.mapToEventListDTO( response );
    }
    @Override
    public EventSeatsDTO getEvent(EventDataDTO eventData){
        HttpResponse<String> response = eventDAO.getEvent(eventData);
        HttpResponseExceptionHandler.checkForException( response );

        return EventMapper.mapToEventSeatsDTO( response );
    }

    @Override
    public DetailedEventDTO getDetailedEvent(EventDataDTO eventData){
        HttpResponse<String> response = eventDAO.getDetailedEvent(eventData);
        HttpResponseExceptionHandler.checkForException( response );

        return EventMapper.mapToDetailedEventDTO(response);
    }
}

