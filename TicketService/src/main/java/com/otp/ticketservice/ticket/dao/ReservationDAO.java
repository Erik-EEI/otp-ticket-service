package com.otp.ticketservice.ticket.dao;

import com.otp.ticketservice.api.dto.PaymentDataDTO;
import com.otp.ticketservice.ticket.utils.HttpRequestUtil;
import com.otp.ticketservice.ticket.utils.UrlBuilder;
import org.springframework.stereotype.Repository;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ReservationDAO {

    private static UrlBuilder urlBuilder;

    public static HttpResponse<String> makeReservation( PaymentDataDTO paymentData ){
        Map<String,String> queryParams = new HashMap<>();
        queryParams.put("eventId", String.valueOf(paymentData.eventId()));
        queryParams.put("seatId", String.valueOf(paymentData.seatId()));

        String url = urlBuilder.buildUrl("reserve");

        return HttpRequestUtil.postRequest(url,queryParams);
    }
}
