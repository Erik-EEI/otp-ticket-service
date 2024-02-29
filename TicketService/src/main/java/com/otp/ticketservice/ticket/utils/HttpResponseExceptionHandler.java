package com.otp.ticketservice.ticket.utils;

import com.otp.ticketservice.ticket.dto.CustomErrorResponseDTO;
import com.otp.ticketservice.ticket.exceptions.CanNotReserveOccupiedSeatException;
import com.otp.ticketservice.ticket.exceptions.EventDoesNotExistException;
import com.otp.ticketservice.ticket.exceptions.SeatDoesNotExistException;
import com.otp.ticketservice.ticket.mapper.ExceptionMapper;
import lombok.experimental.UtilityClass;

import java.net.http.HttpResponse;

@UtilityClass
public class HttpResponseExceptionHandler {
    public static void checkForException(HttpResponse<String> response){
        int statusCode = response.statusCode();;

        if ( statusCode == 200 ) {
            return;
        }

        CustomErrorResponseDTO errorResponse = ExceptionMapper.mapToCustomErrorDTO(response);
        
        switch (errorResponse.errorCode()) {
            case 90001 -> throw new EventDoesNotExistException();
            case 90002 -> throw new SeatDoesNotExistException();
            case 90010 -> throw new CanNotReserveOccupiedSeatException();
        }
    }
}
