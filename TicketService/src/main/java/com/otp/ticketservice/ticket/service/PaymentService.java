package com.otp.ticketservice.ticket.service;

import com.otp.ticketservice.api.dto.EventDataDTO;
import com.otp.ticketservice.api.dto.PaymentDataDTO;
import com.otp.ticketservice.core.interfaces.CoreServiceInterface;
import com.otp.ticketservice.ticket.dao.ReservationDAO;
import com.otp.ticketservice.ticket.dto.detailed_event.DetailedEventDTO;
import com.otp.ticketservice.ticket.dto.event_list.EventDTO;
import com.otp.ticketservice.ticket.dto.payment.PaymentResponseDTO;
import com.otp.ticketservice.ticket.dto.single_event_with_seats.EventSeatsDTO;
import com.otp.ticketservice.ticket.dto.single_event_with_seats.SeatDTO;
import com.otp.ticketservice.ticket.interfaces.EventServiceInterface;
import com.otp.ticketservice.ticket.mapper.EventMapper;
import com.otp.ticketservice.ticket.mapper.PaymentMapper;
import com.otp.ticketservice.ticket.utils.HttpResponseExceptionHandler;
import com.otp.ticketservice.ticket.utils.SeatHandler;
import com.otp.ticketservice.ticket.utils.TimestampHandler;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;

@Service
public class PaymentService {
    private final CoreServiceInterface coreService;
    private final EventServiceInterface eventService;

    public PaymentService(CoreServiceInterface coreService, EventServiceInterface eventService) {
        this.coreService = coreService;
        this.eventService = eventService;
    }

    public PaymentResponseDTO payForReservation(PaymentDataDTO paymentData){
        EventDataDTO eventDataDTO = EventMapper.mapToEventDataDTO( paymentData );
        DetailedEventDTO detailedEvent = eventService.getDetailedEvent(eventDataDTO);
        SeatDTO seat = SeatHandler.getSeat(detailedEvent.seats(), paymentData.seatId());

        TimestampHandler.checkStartTime(detailedEvent.startTimeStamp());
        SeatHandler.validateIfSeatIsReservable(seat);
        coreService.matchCardToUser(paymentData.cardID(), paymentData.userToken());
        coreService.checkIfAmountIsAvailable(paymentData.cardID(), seat.getPrice());

        HttpResponse<String> response = ReservationDAO.makeReservation( paymentData );
        HttpResponseExceptionHandler.checkForException( response );

        return PaymentMapper.mapToPaymentResponseDTO( response );
    }
}

