package com.otp.ticketservice.api.controller;

import com.otp.ticketservice.api.dto.EventDataDTO;
import com.otp.ticketservice.api.dto.PaymentDataDTO;
import com.otp.ticketservice.core.interfaces.CoreServiceInterface;
import com.otp.ticketservice.ticket.dto.payment.PaymentRequestDTO;
import com.otp.ticketservice.ticket.dto.event_list.EventListDTO;
import com.otp.ticketservice.ticket.dto.single_event_with_seats.EventSeatsDTO;
import com.otp.ticketservice.ticket.dto.payment.PaymentResponseDTO;
import com.otp.ticketservice.ticket.interfaces.EventServiceInterface;
import com.otp.ticketservice.ticket.service.EventService;
import com.otp.ticketservice.ticket.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ApiController {

    private final CoreServiceInterface coreService;
    private final PaymentService paymentService; //TODO replace with interface dependency
    private final EventServiceInterface eventService;
    private final Logger LOGGER = LoggerFactory.getLogger("[API MODULE]");

    public ApiController(CoreServiceInterface coreService, PaymentService paymentService, EventService eventService) {
        this.coreService = coreService;
        this.paymentService = paymentService;
        this.eventService = eventService;
    }

    @GetMapping("getEvents")
    public ResponseEntity<EventListDTO> getEvents(
            @RequestHeader(required = true) String userToken
    ) {
        LOGGER.info("GET REQUEST for /getEvents");
        coreService.validateUserToken(userToken);

        EventListDTO events = eventService.getAllEvents();

        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("getEvent")
    public ResponseEntity<EventSeatsDTO> getEventById(
            @RequestParam(required = true) Long eventId,
            @RequestHeader(required = true) String userToken
    ) {
        LOGGER.info("GET REQUEST for /getEvent");
        coreService.validateUserToken(userToken);

        EventDataDTO eventData = new EventDataDTO(eventId);

        EventSeatsDTO eventWithSeats = eventService.getEvent(eventData);

        return new ResponseEntity<>(eventWithSeats, HttpStatus.OK);
    }

    @PostMapping("pay")
    public ResponseEntity<PaymentResponseDTO> getEventById(
            @RequestBody(required = true) PaymentRequestDTO paymentRequest,
            @RequestHeader(required = true) String userToken
    ) {
        LOGGER.info("POST REQUEST for /pay");
        coreService.validateUserToken(userToken);

        PaymentDataDTO paymentData = new PaymentDataDTO(
                paymentRequest.eventId(),
                paymentRequest.seatId(),
                paymentRequest.cardId(),
                userToken
        );

        PaymentResponseDTO result = paymentService.payForReservation(paymentData);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
