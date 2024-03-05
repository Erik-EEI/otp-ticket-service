package com.otp.ticketservice.ticket.interfaces;

import com.otp.ticketservice.api.dto.PaymentDataDTO;

import java.net.http.HttpResponse;

public interface ReservationServiceInterface {
    HttpResponse<String> makeReservation(PaymentDataDTO paymentData);
}
