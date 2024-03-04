package com.otp.ticketservice.ticket.dao;

import com.otp.ticketservice.api.dto.PaymentDataDTO;
import com.otp.ticketservice.ticket.utils.HttpRequestUtil;
import com.otp.ticketservice.ticket.utils.UrlBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Data access object (DAO) responsible for making reservation requests to the external API.
 */
@Repository
public class ReservationDAO {

    private final UrlBuilder urlBuilder;

    @Autowired
    public ReservationDAO(UrlBuilder urlBuilder){
        this.urlBuilder = urlBuilder;
    }

    /**
     * Sends a reservation request to the external API based on the provided payment data.
     *
     * @param paymentData The payment data containing the event ID and seat ID for the reservation.
     * @return The HTTP response indicating the success or failure of the reservation request.
     */
    public HttpResponse<String> makeReservation( PaymentDataDTO paymentData ){
        Map<String,String> queryParams = new HashMap<>();
        queryParams.put("eventId", String.valueOf(paymentData.eventId()));
        queryParams.put("seatId", String.valueOf(paymentData.seatId()));

        String url = urlBuilder.buildUrl("reserve");

        return HttpRequestUtil.postRequest(url,queryParams);
    }
}
