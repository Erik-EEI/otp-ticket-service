package com.otp.ticketservice.ticket.dao;

import com.otp.ticketservice.api.dto.EventDataDTO;
import com.otp.ticketservice.ticket.dto.event_list.EventDTO;
import com.otp.ticketservice.ticket.dto.event_list.EventListResponseDTO;
import com.otp.ticketservice.ticket.dto.single_event_with_seats.EventWithSeatsResponseDTO;
import com.otp.ticketservice.ticket.utils.HttpRequestUtil;
import com.otp.ticketservice.ticket.utils.UrlBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class EventDAO {
    public EventListResponseDTO getAllEvents(){
        String url = UrlBuilder.buildUrl("getEvents");

        return HttpRequestUtil.getRequest(url, EventListResponseDTO.class);
    }

    public EventWithSeatsResponseDTO getEvent(EventDataDTO eventData){
        Map<String,String> params = new HashMap<>();
        params.put("eventId",eventData.eventId().toString());

        String url = UrlBuilder.buildUrl("getEvent",params);

        return HttpRequestUtil.getRequest(url,EventWithSeatsResponseDTO.class);
    }

    public EventDTO getEventDetails(Long eventId){
        EventListResponseDTO allEvents = this.getAllEvents();

        return getAllEvents()
                .getData()
                .stream()
                .filter(event->event.getEventId() == eventId)
                .findFirst()
                .orElseThrow(RuntimeException::new); // TODO Replace exception & review logic
    }
}
