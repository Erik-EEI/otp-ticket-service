package com.otp.ticketservice.ticket.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otp.ticketservice.ticket.dto.CustomErrorResponseDTO;
import com.otp.ticketservice.ticket.dto.payment.PaymentResponseDTO;
import com.otp.ticketservice.ticket.exceptions.UnexpectedResponseFromPartnerException;
import lombok.experimental.UtilityClass;

import java.net.http.HttpResponse;

@UtilityClass
public class ExceptionMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static CustomErrorResponseDTO mapToCustomErrorDTO(HttpResponse<String> response){
        try{
            return objectMapper.readValue(response.body(), CustomErrorResponseDTO.class);
        } catch (Exception e){
            throw new UnexpectedResponseFromPartnerException();
        }
    }
}
