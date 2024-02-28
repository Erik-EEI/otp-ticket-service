package com.otp.ticketservice.ticket.service;

import com.otp.ticketservice.ticket.dto.EventResponseDTO;
import com.otp.ticketservice.ticket.utils.HttpRequestUtil;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    public EventResponseDTO getAllEvents(){
        String events = HttpRequestUtil.getRequest("http://127.0.0.1:8081/getEvents");

        return new EventResponseDTO(events);
    }

    public EventResponseDTO getEvent(Long eventId){
        String event = HttpRequestUtil.getRequest(String.format("http://127.0.0.1:8081/getEvent?eventId=%s",eventId)); //TODO Handle URL building

        return new EventResponseDTO(event);
    }
}
