package com.otp.ticketservice.ticket.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otp.ticketservice.ticket.dto.payment.PaymentResponseDTO;
import com.otp.ticketservice.ticket.exceptions.UnexpectedResponseFromPartnerException;
import lombok.experimental.UtilityClass;

import java.net.http.HttpResponse;

@UtilityClass
public class PaymentMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static PaymentResponseDTO mapToPaymentResponseDTO(HttpResponse<String> response){
        try{
            return objectMapper.readValue(response.body(), PaymentResponseDTO.class);
        } catch (Exception e){
            throw new UnexpectedResponseFromPartnerException();
        }
    }
}
