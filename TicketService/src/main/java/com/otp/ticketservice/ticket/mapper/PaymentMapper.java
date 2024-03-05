package com.otp.ticketservice.ticket.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otp.ticketservice.ticket.dto.payment.PaymentResponseDTO;
import lombok.experimental.UtilityClass;

import java.net.http.HttpResponse;

@UtilityClass
public class PaymentMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static PaymentResponseDTO mapToPaymentResponseDTO(HttpResponse<String> response){
        try{
            return objectMapper.readValue(response.body(), PaymentResponseDTO.class);
        } catch (Exception e){
            throw new RuntimeException("Error parsing Partner response"); //TODO Handle custom exception
        }
    }
}
