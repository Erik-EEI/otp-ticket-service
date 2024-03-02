package com.otp.ticketservice.ticket.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otp.ticketservice.api.dto.EventDataDTO;
import com.otp.ticketservice.api.dto.PaymentDataDTO;
import com.otp.ticketservice.ticket.dto.detailed_event.DetailedEventDTO;
import com.otp.ticketservice.ticket.dto.detailed_event.DetailedEventWrapperDTO;
import com.otp.ticketservice.ticket.dto.event_list.EventListDTO;
import com.otp.ticketservice.ticket.dto.single_event_with_seats.EventSeatsDTO;
import com.otp.ticketservice.ticket.dto.single_event_with_seats.EventSeatsWrapperDTO;
import com.otp.ticketservice.ticket.exceptions.UnexpectedResponseFromPartnerException;
import lombok.experimental.UtilityClass;

import java.net.http.HttpResponse;

@UtilityClass
public class EventMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static EventListDTO mapToEventListDTO(HttpResponse<String> response){
        try{
            return objectMapper.readValue(response.body(),EventListDTO.class);
        } catch (Exception e){
            throw new UnexpectedResponseFromPartnerException();
        }
    }

    public static EventSeatsDTO mapToEventSeatsDTO(HttpResponse<String> response){
        try{
            EventSeatsWrapperDTO wrapperDTO = objectMapper.readValue(response.body(),EventSeatsWrapperDTO.class);
            return wrapperDTO.data();
        } catch (Exception e){
            throw new UnexpectedResponseFromPartnerException();
        }
    }

    public static DetailedEventDTO mapToDetailedEventDTO( HttpResponse<String> response ){
        try{
            DetailedEventWrapperDTO wrapperDTO = objectMapper.readValue(response.body(),DetailedEventWrapperDTO.class);
            return wrapperDTO.data();
        } catch (Exception e){
            throw new UnexpectedResponseFromPartnerException();
        }
    }

    public static EventDataDTO mapToEventDataDTO(PaymentDataDTO paymentDataDTO){
        return new EventDataDTO(paymentDataDTO.eventId() );
    }
}
