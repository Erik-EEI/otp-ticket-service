package com.otp.ticketservice.ticket.service;

import com.otp.ticketservice.api.dto.PaymentDataDTO;
import com.otp.ticketservice.ticket.dao.ReservationDAO;
import com.otp.ticketservice.ticket.utils.HttpResponseExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;

@Service
public class ReservationService {
    private final ReservationDAO reservationDAO;
    private final Logger LOGGER = LoggerFactory.getLogger("[TICKET - RESERVATION SERVICE]");

    public ReservationService(ReservationDAO reservationDAO) {
        this.reservationDAO = reservationDAO;
    }


    public HttpResponse<String> makeReservation(PaymentDataDTO paymentData){
        HttpResponse<String> response =reservationDAO.makeReservation( paymentData );
        LOGGER.info("SENT reservation request to partner");
        HttpResponseExceptionHandler.checkForException( response );
        LOGGER.info("RECEIVED reservation response from partner");

        return response;
    }
}
