package com.otp.ticketservice.ticket.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otp.ticketservice.ticket.dto.event_list.EventListDTO;
import com.otp.ticketservice.ticket.dto.single_event_with_seats.EventSeatsDTO;
import com.otp.ticketservice.ticket.dto.single_event_with_seats.EventSeatsWrapperDTO;
import lombok.experimental.UtilityClass;

import java.net.http.HttpResponse;

@UtilityClass
public class EventMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static EventListDTO mapToEventListDTO(HttpResponse<String> response){
        try{
            return objectMapper.readValue(response.body(),EventListDTO.class);
        } catch (Exception e){
            throw new RuntimeException("Error parsing Partner response"); //TODO Handle custom exception
        }
    }

    public static EventSeatsDTO mapToEventSeatsDTO(HttpResponse<String> response){
        try{
            EventSeatsWrapperDTO wrapperDTO = objectMapper.readValue(response.body(),EventSeatsWrapperDTO.class);
            return wrapperDTO.getData();
        } catch (Exception e){
            throw new RuntimeException("Error parsing Partner response"); //TODO Handle custom exception
        }
    }
}
