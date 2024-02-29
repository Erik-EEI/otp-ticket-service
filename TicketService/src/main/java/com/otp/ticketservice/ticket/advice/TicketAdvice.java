package com.otp.ticketservice.ticket.advice;

import com.otp.ticketservice.api.dto.response.ApiErrorResponse;
import com.otp.ticketservice.core.exception.*;
import com.otp.ticketservice.ticket.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.channels.ClosedChannelException;

@RestControllerAdvice
public class TicketAdvice {
    @ExceptionHandler(EventDoesNotExistException.class)
    public ResponseEntity<ApiErrorResponse> handleEventNotFoundException(EventDoesNotExistException ex) {
        ApiErrorResponse response = new ApiErrorResponse(ex.getMessage(), ex.getErrorCode(),false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(SeatDoesNotExistException.class)
    public ResponseEntity<ApiErrorResponse> handleEventNotFoundException(SeatDoesNotExistException ex) {
        ApiErrorResponse response = new ApiErrorResponse(ex.getMessage(), ex.getErrorCode(),false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CanNotReserveOccupiedSeatException.class)
    public ResponseEntity<ApiErrorResponse> handleEventNotFoundException(CanNotReserveOccupiedSeatException ex) {
        ApiErrorResponse response = new ApiErrorResponse(ex.getMessage(), ex.getErrorCode(),false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CanNotReserveSeatForEventInPastException.class)
    public ResponseEntity<ApiErrorResponse> handleEventNotFoundException(CanNotReserveSeatForEventInPastException ex) {
        ApiErrorResponse response = new ApiErrorResponse(ex.getMessage(), ex.getErrorCode(),false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(PartnerServerErrorException.class)
    public ResponseEntity<ApiErrorResponse> handleEventNotFoundException(PartnerServerErrorException ex) {
        ApiErrorResponse response = new ApiErrorResponse(ex.getMessage(), ex.getErrorCode(),false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ClosedChannelException.class)
    public ResponseEntity<ApiErrorResponse> handleEventNotFoundException(ClosedChannelException ex) {
        ExternalSystemNotAvailableException customEx = new ExternalSystemNotAvailableException();
        ApiErrorResponse response = new ApiErrorResponse(customEx.getMessage(), customEx.getErrorCode(),false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}

