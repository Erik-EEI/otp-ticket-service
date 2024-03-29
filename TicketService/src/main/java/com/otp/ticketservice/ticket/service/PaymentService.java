package com.otp.ticketservice.ticket.service;

import com.otp.ticketservice.api.dto.EventDataDTO;
import com.otp.ticketservice.api.dto.PaymentDataDTO;
import com.otp.ticketservice.core.interfaces.CoreServiceInterface;
import com.otp.ticketservice.ticket.dto.detailed_event.DetailedEventDTO;
import com.otp.ticketservice.ticket.dto.payment.PaymentResponseDTO;
import com.otp.ticketservice.ticket.dto.single_event_with_seats.SeatDTO;
import com.otp.ticketservice.ticket.interfaces.EventServiceInterface;
import com.otp.ticketservice.ticket.interfaces.PaymentServiceInterface;
import com.otp.ticketservice.ticket.interfaces.ReservationServiceInterface;
import com.otp.ticketservice.ticket.mapper.EventMapper;
import com.otp.ticketservice.ticket.mapper.PaymentMapper;
import com.otp.ticketservice.ticket.utils.SeatHandler;
import com.otp.ticketservice.ticket.utils.TimestampHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;

/**
 * Service class for managing payments and reservations.
 */
@Service
public class PaymentService implements PaymentServiceInterface {
    private final CoreServiceInterface coreService;
    private final EventServiceInterface eventService;
    private final ReservationServiceInterface reservationService;
    private final Logger LOGGER = LoggerFactory.getLogger("[TICKET - PAYMENT SERVICE]");

    @Value("${api.timestamp.validation}")
    private boolean validateTimeStamp;

    @Autowired
    public PaymentService(CoreServiceInterface coreService, EventServiceInterface eventService, ReservationServiceInterface reservationService) {
        this.coreService = coreService;
        this.eventService = eventService;
        this.reservationService = reservationService;
    }

    /**
     * Processes a payment for a reservation and makes the reservation.
     * @param paymentData the payment data including user token, card ID, seat ID, and event data
     * @return a DTO containing payment response information including reservation ID
     */
    @Override
    public PaymentResponseDTO payForReservation(PaymentDataDTO paymentData){
        EventDataDTO eventDataDTO = EventMapper.mapToEventDataDTO( paymentData );
        DetailedEventDTO detailedEvent = eventService.getDetailedEvent(eventDataDTO);
        SeatDTO seat = SeatHandler.getSeat(detailedEvent.seats(), paymentData.seatId());

        if(validateTimeStamp){
        TimestampHandler.checkStartTime(detailedEvent.startTimeStamp());
        }
        SeatHandler.validateIfSeatIsReservable(seat);
        coreService.matchCardToUser(paymentData.cardID(), paymentData.userToken());
        coreService.payWithCard(paymentData.cardID(), seat.price());

        HttpResponse<String> response = reservationService.makeReservation( paymentData );
        PaymentResponseDTO reservationData = PaymentMapper.mapToPaymentResponseDTO( response );
        LOGGER.info(String.format("✔ - COMPLETED -- RESERVATION MADE with id -> %d", reservationData.reservationId()));


        return reservationData;
    }
}

