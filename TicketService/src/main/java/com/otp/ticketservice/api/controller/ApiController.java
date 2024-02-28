package com.otp.ticketservice.api.controller;

import com.otp.ticketservice.api.dto.EventDataDTO;
import com.otp.ticketservice.api.dto.PaymentDataDTO;
import com.otp.ticketservice.core.interfaces.CoreServiceInterface;
import com.otp.ticketservice.ticket.dto.event_list.EventListResponseDTO;
import com.otp.ticketservice.ticket.dto.single_event_with_seats.EventWithSeatsResponseDTO;
import com.otp.ticketservice.ticket.dto.PaymentResponseDTO;
import com.otp.ticketservice.ticket.interfaces.EventServiceInterface;
import com.otp.ticketservice.ticket.service.EventService;
import com.otp.ticketservice.ticket.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ApiController {

    private final CoreServiceInterface coreService;
    private final PaymentService paymentService; //TODO replace with interface dependency
    private final EventServiceInterface eventService;

    public ApiController(CoreServiceInterface coreService, PaymentService paymentService, EventService eventService) {
        this.coreService = coreService;
        this.paymentService = paymentService;
        this.eventService = eventService;
    }

    @GetMapping("getEvents")
    public ResponseEntity<EventListResponseDTO> getEvents(
            @RequestHeader(required = true) String userToken
    ) {
        coreService.validateUserToken(userToken);

        EventListResponseDTO events = eventService.getAllEvents();

        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("getEvent")
    public ResponseEntity<EventWithSeatsResponseDTO> getEventById(
            @RequestParam(required = true) Long eventId,
            @RequestHeader(required = true) String userToken
    ) {
        coreService.validateUserToken(userToken);
        
        EventDataDTO eventData = new EventDataDTO(eventId);

        EventWithSeatsResponseDTO eventWithSeats = eventService.getEvent(eventData);

        return new ResponseEntity<>(eventWithSeats, HttpStatus.OK);
    }

    @PostMapping("pay")
    public ResponseEntity<PaymentResponseDTO> getEventById(
            @RequestParam(required = true) Long eventId,
            @RequestParam(required = true) Long seatId,
            @RequestParam(required = true) Long cardId,
            @RequestHeader(required = true) String userToken
    ) {
        coreService.validateUserToken(userToken);

        PaymentDataDTO paymentData = new PaymentDataDTO(eventId,seatId,cardId);



        return new ResponseEntity<>(new PaymentResponseDTO(), HttpStatus.OK);
    }
}
