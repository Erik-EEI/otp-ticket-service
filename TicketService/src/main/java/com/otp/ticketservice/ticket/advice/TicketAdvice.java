package com.otp.ticketservice.ticket.advice;

import com.otp.ticketservice.api.dto.response.ApiErrorResponse;
import com.otp.ticketservice.ticket.exceptions.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.channels.ClosedChannelException;

/**
 * Controller advice to handle exceptions specific to the Ticket module.
 */
@RestControllerAdvice
public class TicketAdvice {
    private final Logger LOGGER = LoggerFactory.getLogger("[TICKET MODULE]");

    /**
     * Handles exceptions of type EventDoesNotExistException and returns an appropriate HTTP response.
     *
     * @param ex The exception to handle.
     * @return An HTTP response entity with the error details.
     */
    @ExceptionHandler(EventDoesNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ApiResponse(responseCode = "404", description = "Event not found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
    public ResponseEntity<ApiErrorResponse> handleEventDoesNotExistException(EventDoesNotExistException ex) {
        LOGGER.error(String.format("❌ - / %s / - %s",ex.getErrorCode(),ex.getMessage()));
        ApiErrorResponse response = new ApiErrorResponse(ex.getMessage(), ex.getErrorCode(),false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles exceptions of type SeatDoesNotExistException and returns an appropriate HTTP response.
     *
     * @param ex The exception to handle.
     * @return An HTTP response entity with the error details.
     */
    @ExceptionHandler(SeatDoesNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ApiResponse(responseCode = "404", description = "Seat not found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
    public ResponseEntity<ApiErrorResponse> handleSeatDoesNotExistException(SeatDoesNotExistException ex) {
        LOGGER.error(String.format("❌ - / %s / - %s",ex.getErrorCode(),ex.getMessage()));
        ApiErrorResponse response = new ApiErrorResponse(ex.getMessage(), ex.getErrorCode(),false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles exceptions of type CanNotReserveOccupiedSeatException and returns an appropriate HTTP response.
     *
     * @param ex The exception to handle.
     * @return An HTTP response entity with the error details.
     */
    @ExceptionHandler(CanNotReserveOccupiedSeatException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ApiResponse(responseCode = "401", description = "Can not reserve already occupied seat",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
    public ResponseEntity<ApiErrorResponse> handleCanNotReserveOccupiedSeatException(CanNotReserveOccupiedSeatException ex) {
        LOGGER.error(String.format("❌ - / %s / - %s",ex.getErrorCode(),ex.getMessage()));
        ApiErrorResponse response = new ApiErrorResponse(ex.getMessage(), ex.getErrorCode(),false);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    /**
     * Handles exceptions of type CanNotReserveSeatForEventInPastException and returns an appropriate HTTP response.
     *
     * @param ex The exception to handle.
     * @return An HTTP response entity with the error details.
     */
    @ExceptionHandler(CanNotReserveSeatForEventInPastException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ApiResponse(responseCode = "401", description = "Can not reserve seat for event in the past",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
    public ResponseEntity<ApiErrorResponse> handleCanNotReserveSeatForEventInPastException(CanNotReserveSeatForEventInPastException ex) {
        LOGGER.error(String.format("❌ - / %s / - %s",ex.getErrorCode(),ex.getMessage()));
        ApiErrorResponse response = new ApiErrorResponse(ex.getMessage(), ex.getErrorCode(),false);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    /**
     * Handles exceptions of type PartnerServerErrorException and returns an appropriate HTTP response.
     *
     * @param ex The exception to handle.
     * @return An HTTP response entity with the error details.
     */
    @ExceptionHandler(PartnerServerErrorException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ApiResponse(responseCode = "503", description = "Error in partner's server",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
    public ResponseEntity<ApiErrorResponse> handlePartnerServerErrorException(PartnerServerErrorException ex) {
        LOGGER.error(String.format("❌ - / %s / - %s",ex.getErrorCode(),ex.getMessage()));
        ApiErrorResponse response = new ApiErrorResponse(ex.getMessage(), ex.getErrorCode(),false);
        return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
    }

    /**
     * Handles exceptions of type ClosedChannelException and returns an appropriate HTTP response.
     *
     * @param ex The exception to handle.
     * @return An HTTP response entity with the error details.
     */
    @ExceptionHandler(ClosedChannelException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ApiResponse(responseCode = "503", description = "Partner server unreachable",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
    public ResponseEntity<ApiErrorResponse> handleClosedChannelException(ClosedChannelException ex) {
        ExternalSystemNotAvailableException customEx = new ExternalSystemNotAvailableException();
        ApiErrorResponse response = new ApiErrorResponse(customEx.getMessage(), customEx.getErrorCode(),false);
        LOGGER.error(String.format("❌ - / %s / - %s",response.errorCode(),response.message()));
        return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
    }

    /**
     * Handles exceptions of type UnexpectedResponseFromPartnerException and returns an appropriate HTTP response.
     *
     * @param ex The exception to handle.
     * @return An HTTP response entity with the error details.
     */
    @ExceptionHandler(UnexpectedResponseFromPartnerException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ApiResponse(responseCode = "401", description = "Unexpected response from partner",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
    public ResponseEntity<ApiErrorResponse> handleUnexpectedResponseFromPartnerException(UnexpectedResponseFromPartnerException ex) {
        ExternalSystemNotAvailableException customEx = new ExternalSystemNotAvailableException();
        ApiErrorResponse response = new ApiErrorResponse(customEx.getMessage(), customEx.getErrorCode(),false);
        LOGGER.error(String.format("❌ - / %s / - %s",response.errorCode(),response.message()));
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

}

