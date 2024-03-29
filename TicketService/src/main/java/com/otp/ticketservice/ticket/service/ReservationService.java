package com.otp.ticketservice.ticket.service;

import com.otp.ticketservice.api.dto.PaymentDataDTO;
import com.otp.ticketservice.ticket.dao.ReservationDAO;
import com.otp.ticketservice.ticket.interfaces.ReservationServiceInterface;
import com.otp.ticketservice.ticket.utils.HttpResponseExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;

/**
 * Service class for managing reservations with external api.
 */
@Service
public class ReservationService implements ReservationServiceInterface {
    private final ReservationDAO reservationDAO;
    private final Logger LOGGER = LoggerFactory.getLogger("[TICKET - RESERVATION SERVICE]");

    @Autowired
    public ReservationService(ReservationDAO reservationDAO) {
        this.reservationDAO = reservationDAO;
    }

    /**
     * Makes a reservation using the provided payment data.
     * @param paymentData the payment data for making the reservation
     * @return the HTTP response containing the result of the reservation request
     */
    @Override
    public HttpResponse<String> makeReservation(PaymentDataDTO paymentData){
        HttpResponse<String> response =reservationDAO.makeReservation( paymentData );
        LOGGER.info("SENT reservation request to partner");
        HttpResponseExceptionHandler.checkForException( response );
        LOGGER.info("RECEIVED reservation response from partner");

        return response;
    }
}
