package com.otp.ticketservice.ticket.service;

import com.otp.ticketservice.api.dto.EventDataDTO;
import com.otp.ticketservice.api.dto.PaymentDataDTO;
import com.otp.ticketservice.core.interfaces.CoreServiceInterface;
import com.otp.ticketservice.ticket.dto.PaymentResponseDTO;
import com.otp.ticketservice.ticket.dto.single_event_with_seats.EventDataWithSeatsDTO;
import com.otp.ticketservice.ticket.dto.single_event_with_seats.EventWithSeatsResponseDTO;
import com.otp.ticketservice.ticket.dto.single_event_with_seats.SeatDTO;
import com.otp.ticketservice.ticket.interfaces.EventServiceInterface;
import org.springframework.stereotype.Service;

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
        SeatDTO seat = event
                .getData()
                .getSeats()
                .stream()
                .filter(seatDTO-> seatDTO.getId() == paymentData.seatId())
                .findFirst()
                .orElseThrow(RuntimeException::new); //TODO Replace with custom exception

        coreService.matchCardToUser(paymentData.cardID(), paymentData.userToken());
        coreService.checkIfAmountIsAvailable(paymentData.cardID(), seat.getPrice());

        return new PaymentResponseDTO(); // TODO For testing
    }
}
