package com.otp.ticketservice.ticket.advice;

import com.otp.ticketservice.api.dto.response.ApiErrorResponse;
import com.otp.ticketservice.core.exception.*;
import com.otp.ticketservice.ticket.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.channels.ClosedChannelException;

@RestControllerAdvice
public class TicketAdvice {
    private final Logger LOGGER = LoggerFactory.getLogger("[TICKET MODULE]");
    @ExceptionHandler(EventDoesNotExistException.class)
    public ResponseEntity<ApiErrorResponse> handleEventNotFoundException(EventDoesNotExistException ex) {
        LOGGER.error(String.format("❌ - / %s / - %s",ex.getErrorCode(),ex.getMessage()));
        ApiErrorResponse response = new ApiErrorResponse(ex.getMessage(), ex.getErrorCode(),false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(SeatDoesNotExistException.class)
    public ResponseEntity<ApiErrorResponse> handleEventNotFoundException(SeatDoesNotExistException ex) {
        LOGGER.error(String.format("❌ - / %s / - %s",ex.getErrorCode(),ex.getMessage()));
        ApiErrorResponse response = new ApiErrorResponse(ex.getMessage(), ex.getErrorCode(),false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CanNotReserveOccupiedSeatException.class)
    public ResponseEntity<ApiErrorResponse> handleEventNotFoundException(CanNotReserveOccupiedSeatException ex) {
        LOGGER.error(String.format("❌ - / %s / - %s",ex.getErrorCode(),ex.getMessage()));
        ApiErrorResponse response = new ApiErrorResponse(ex.getMessage(), ex.getErrorCode(),false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CanNotReserveSeatForEventInPastException.class)
    public ResponseEntity<ApiErrorResponse> handleEventNotFoundException(CanNotReserveSeatForEventInPastException ex) {
        LOGGER.error(String.format("❌ - / %s / - %s",ex.getErrorCode(),ex.getMessage()));
        ApiErrorResponse response = new ApiErrorResponse(ex.getMessage(), ex.getErrorCode(),false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(PartnerServerErrorException.class)
    public ResponseEntity<ApiErrorResponse> handleEventNotFoundException(PartnerServerErrorException ex) {
        LOGGER.error(String.format("❌ - / %s / - %s",ex.getErrorCode(),ex.getMessage()));
        ApiErrorResponse response = new ApiErrorResponse(ex.getMessage(), ex.getErrorCode(),false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ClosedChannelException.class)
    public ResponseEntity<ApiErrorResponse> handleEventNotFoundException(ClosedChannelException ex) {
        ExternalSystemNotAvailableException customEx = new ExternalSystemNotAvailableException();
        ApiErrorResponse response = new ApiErrorResponse(customEx.getMessage(), customEx.getErrorCode(),false);
        LOGGER.error(String.format("❌ - / %s / - %s",response.errorCode(),response.message()));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}

