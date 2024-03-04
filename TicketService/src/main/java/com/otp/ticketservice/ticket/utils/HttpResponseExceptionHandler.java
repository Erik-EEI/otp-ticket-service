package com.otp.ticketservice.ticket.utils;

import com.otp.ticketservice.ticket.dto.CustomErrorResponseDTO;
import com.otp.ticketservice.ticket.exceptions.CanNotReserveOccupiedSeatException;
import com.otp.ticketservice.ticket.exceptions.EventDoesNotExistException;
import com.otp.ticketservice.ticket.exceptions.PartnerServerErrorException;
import com.otp.ticketservice.ticket.exceptions.SeatDoesNotExistException;
import com.otp.ticketservice.ticket.mapper.ExceptionMapper;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpResponse;

/**
 * Utility class for handling HTTP response exceptions from partner modules.
 */
@UtilityClass
public class HttpResponseExceptionHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(HttpResponseExceptionHandler.class);

    /**
     * Checks the HTTP response for any error and throws corresponding exceptions if necessary.
     * @param response the HTTP response received from the partner module
     * @throws EventDoesNotExistException if the event does not exist in the partner's database
     * @throws SeatDoesNotExistException if the requested seat does not exist for the event
     * @throws CanNotReserveOccupiedSeatException if an attempt is made to reserve an already occupied seat
     * @throws PartnerServerErrorException if the partner module encounters a server error
     */
    public static void checkForException(HttpResponse<String> response){
        int statusCode = response.statusCode();;

        if ( statusCode == 200 ) {
            return;
        }

        LOGGER.error("Exception from partner module");
        CustomErrorResponseDTO errorResponse = ExceptionMapper.mapToCustomErrorDTO(response);

        switch (errorResponse.errorCode()) {
            case 90001 -> throw new EventDoesNotExistException();
            case 90002 -> throw new SeatDoesNotExistException();
            case 90010 -> throw new CanNotReserveOccupiedSeatException();
            case 90000 -> throw new PartnerServerErrorException();
        }
    }
}
