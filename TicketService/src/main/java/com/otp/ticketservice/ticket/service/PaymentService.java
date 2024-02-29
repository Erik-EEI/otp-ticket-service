package com.otp.ticketservice.ticket.service;

import com.otp.ticketservice.api.dto.EventDataDTO;
import com.otp.ticketservice.api.dto.PaymentDataDTO;
import com.otp.ticketservice.core.interfaces.CoreServiceInterface;
import com.otp.ticketservice.ticket.dto.payment.PaymentResponseDTO;
import com.otp.ticketservice.ticket.dto.single_event_with_seats.EventWithSeatsResponseDTO;
import com.otp.ticketservice.ticket.dto.single_event_with_seats.SeatDTO;
import com.otp.ticketservice.ticket.interfaces.EventServiceInterface;
import com.otp.ticketservice.ticket.utils.HttpRequestUtil;
import com.otp.ticketservice.ticket.utils.SeatHandler;
import com.otp.ticketservice.ticket.utils.UrlBuilder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {
    private final CoreServiceInterface coreService;
    private final EventServiceInterface eventService;

    public PaymentService(CoreServiceInterface coreService, EventServiceInterface eventService) {
        this.coreService = coreService;
        this.eventService = eventService;
    }

    public PaymentResponseDTO payForReservation(PaymentDataDTO paymentData){
        EventWithSeatsResponseDTO event = eventService.getEvent(new EventDataDTO(paymentData.eventId()));
        SeatDTO seat = SeatHandler.getSeat(event, paymentData.seatId());

        SeatHandler.validateIfSeatIsReservable(seat);
        coreService.matchCardToUser(paymentData.cardID(), paymentData.userToken());
        coreService.checkIfAmountIsAvailable(paymentData.cardID(), seat.getPrice());

        Map<String,String> queryParams = new HashMap<>();
        queryParams.put("eventId", String.valueOf(paymentData.eventId()));
        queryParams.put("seatId", String.valueOf(paymentData.seatId()));

        String url = UrlBuilder.buildUrl("reserve");

        return HttpRequestUtil.postRequest(url,queryParams,PaymentResponseDTO.class);
    }
}
