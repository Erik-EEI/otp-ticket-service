package com.otp.ticketservice.ticket.service;

import com.otp.ticketservice.api.dto.EventDataDTO;
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
    @Override
    public EventListResponseDTO getAllEvents(){
        String url = UrlBuilder.buildUrl("getEvents");

        return HttpRequestUtil.getRequest(url, EventListResponseDTO.class);
    }
    @Override
    public EventWithSeatsResponseDTO getEvent(EventDataDTO eventData){
        Map<String,String> params = new HashMap<>();
        params.put("eventId",eventData.eventId().toString());

        String url = UrlBuilder.buildUrl("getEvent",params);

        return HttpRequestUtil.getRequest(url,EventWithSeatsResponseDTO.class);
    }
}

