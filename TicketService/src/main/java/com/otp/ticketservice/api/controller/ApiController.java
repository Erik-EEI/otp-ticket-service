package com.otp.ticketservice.api.controller;

import com.otp.ticketservice.api.dto.EventDataDTO;
import com.otp.ticketservice.api.dto.PaymentDataDTO;
import com.otp.ticketservice.api.dto.response.ApiErrorResponse;
import com.otp.ticketservice.core.interfaces.CoreServiceInterface;
import com.otp.ticketservice.ticket.dto.payment.PaymentRequestDTO;
import com.otp.ticketservice.ticket.dto.event_list.EventListDTO;
import com.otp.ticketservice.ticket.dto.single_event_with_seats.EventSeatsDTO;
import com.otp.ticketservice.ticket.dto.payment.PaymentResponseDTO;
import com.otp.ticketservice.ticket.interfaces.EventServiceInterface;
import com.otp.ticketservice.ticket.interfaces.PaymentServiceInterface;
import com.otp.ticketservice.ticket.service.EventService;
import com.otp.ticketservice.ticket.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ApiController {

    private final CoreServiceInterface coreService;
    private final PaymentServiceInterface paymentService;
    private final EventServiceInterface eventService;
    private final Logger LOGGER = LoggerFactory.getLogger("[API MODULE]");
    private final String DEFAULT_USER_TOKEN = "dGVzenQuYWxhZGFyQG90cG1vYmlsLmNvbSYxMDAwJkY2N0MyQkNCRkNGQTMwRkNDQjM2RjcyRENBMjJBODE3";

    @Autowired
    public ApiController(CoreServiceInterface coreService, PaymentService paymentService, EventService eventService) {
        this.coreService = coreService;
        this.paymentService = paymentService;
        this.eventService = eventService;
    }

    @Operation(summary = "Get all events")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returned all events",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = EventListDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Invalid user token",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))}),
            @ApiResponse(responseCode = "503", description = "Partner server error or unavailable",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))})
    })
    @GetMapping("getEvents")
    public ResponseEntity<EventListDTO> getEvents(
            @Parameter(in = ParameterIn.HEADER, required = true, description = "User Token", example=DEFAULT_USER_TOKEN)
            @RequestHeader(required = true) String userToken
    ) {
        LOGGER.info(" » GET REQUEST for /getEvents");
        coreService.validateUserToken(userToken);

        EventListDTO events = eventService.getAllEvents();

        LOGGER.info(" « RESPONSE sent to GET Request from /getEvents");
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @Operation(summary = "Get a specific event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returned a specific event",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = EventListDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Invalid user token",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))}),
            @ApiResponse(responseCode = "503", description = "Partner server error or unavailable",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Event not found",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))})
    })
    @GetMapping("getEvent")
    public ResponseEntity<EventSeatsDTO> getEventById(
            @Parameter(in = ParameterIn.DEFAULT, required = true, description = "ID of the required event", example="1")
            @RequestParam(required = true) Long eventId,
            @Parameter(in = ParameterIn.HEADER, required = true, description = "User Token", example=DEFAULT_USER_TOKEN)
            @RequestHeader(required = true) String userToken
    ) {
        LOGGER.info(" » GET REQUEST for /getEvent");
        coreService.validateUserToken(userToken);

        EventDataDTO eventData = new EventDataDTO(eventId);

        EventSeatsDTO eventWithSeats = eventService.getEvent(eventData);

        LOGGER.info(" « RESPONSE sent to GET Request from /getEvent");
        return new ResponseEntity<>(eventWithSeats, HttpStatus.OK);
    }

    @Operation(summary = "Make a reservation for an event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation successful",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Invalid user token or card",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Event or seat not found",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))}),
            @ApiResponse(responseCode = "503", description = "Partner server error or unavailable",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))})
    })
    @PostMapping("pay")
    public ResponseEntity<PaymentResponseDTO> getEventById(
            @Parameter(required = true, description = "Data required for reserving a seat at an event")
            @RequestBody(required = true) PaymentRequestDTO paymentRequest,
            @Parameter(in = ParameterIn.HEADER, required = true, description = "User Token", example=DEFAULT_USER_TOKEN)
            @RequestHeader(required = true) String userToken
    ) {
        LOGGER.info(" » POST REQUEST for /pay");
        coreService.validateUserToken(userToken);

        PaymentDataDTO paymentData = new PaymentDataDTO(
                paymentRequest.eventId(),
                paymentRequest.seatId(),
                paymentRequest.cardId(),
                userToken
        );

        PaymentResponseDTO result = paymentService.payForReservation(paymentData);

        LOGGER.info(" « RESPONSE sent to POST Request from /pay");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
