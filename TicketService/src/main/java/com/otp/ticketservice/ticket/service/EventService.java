package com.otp.ticketservice.ticket.service;

import com.otp.ticketservice.api.dto.EventDataDTO;
import com.otp.ticketservice.ticket.dao.EventDAO;
import com.otp.ticketservice.ticket.dto.detailed_event.DetailedEventDTO;
import com.otp.ticketservice.ticket.dto.event_list.EventListDTO;
import com.otp.ticketservice.ticket.dto.single_event_with_seats.EventSeatsDTO;
import com.otp.ticketservice.ticket.interfaces.EventServiceInterface;
import com.otp.ticketservice.ticket.mapper.EventMapper;
import com.otp.ticketservice.ticket.utils.HttpResponseExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;

/**
 * Service class for retrieving events from external API.
 */
@Service
public class EventService implements EventServiceInterface {

    private final EventDAO eventDAO;
    private final Logger LOGGER = LoggerFactory.getLogger("[TICKET - EVENT SERVICE]");

    @Autowired
    public EventService(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    /**
     * Retrieves all events.
     * @return a DTO containing a list of events
     */
    @Override
    @Cacheable("all_events")
    public EventListDTO getAllEvents(){
        HttpResponse<String> response = eventDAO.getAllEvents();
        LOGGER.info("- ⏩ - SENT events request to partner");
        HttpResponseExceptionHandler.checkForException( response );
        LOGGER.info("- ⏪ - RECEIVED events response from partner");

        return EventMapper.mapToEventListDTO( response );
    }

    /**
     * Retrieves event id with the list of seats at the event.
     * @param eventData the data of the event to retrieve
     * @return a DTO containing event id and a list of its seats
     */
    @Override
    public EventSeatsDTO getEvent(EventDataDTO eventData){
        HttpResponse<String> response = eventDAO.getEvent(eventData);
        LOGGER.info(String.format("- ⏩ - SENT event request to partner for event id %s", eventData.eventId()));
        HttpResponseExceptionHandler.checkForException( response );
        LOGGER.info(String.format("- ⏪ - RECEIVED event response from partner for event id %s", eventData.eventId()));

        return EventMapper.mapToEventSeatsDTO( response );
    }

    /**
     * Retrieves detailed event information.
     * @param eventData the data of the event to retrieve
     * @return a DTO containing detailed event information
     */
    @Override
    public DetailedEventDTO getDetailedEvent(EventDataDTO eventData){
        HttpResponse<String> response = eventDAO.getDetailedEvent(eventData);
        LOGGER.info(String.format("- ⏩ - SENT event request to partner for event id %s", eventData.eventId()));
        HttpResponseExceptionHandler.checkForException( response );
        LOGGER.info(String.format("- ⏪ - Received detailed event response from partner for event id %s", eventData.eventId()));

        return EventMapper.mapToDetailedEventDTO(response);
    }

}

