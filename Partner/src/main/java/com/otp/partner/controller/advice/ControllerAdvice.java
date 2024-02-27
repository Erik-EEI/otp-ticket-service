package com.otp.partner.controller.advice;

import com.otp.partner.dto.response.ApiErrorResponse;
import com.otp.partner.exception.EventNotFoundException;
import com.otp.partner.exception.ReservationNotFoundException;
import com.otp.partner.exception.SeatAlreadyReservedException;
import com.otp.partner.exception.SeatNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.otp.partner.controller")
public class ControllerAdvice {

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleEventNotFoundException(EventNotFoundException ex) {
        ApiErrorResponse response = new ApiErrorResponse(ex.getMessage(), ex.getErrorCode(),false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleReservationNotFoundException(ReservationNotFoundException ex) {
        ApiErrorResponse response = new ApiErrorResponse(ex.getMessage(), ex.getErrorCode(),false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SeatNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleSeatNotFoundException(SeatNotFoundException ex) {
        ApiErrorResponse response = new ApiErrorResponse(ex.getMessage(), ex.getErrorCode(),false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(SeatAlreadyReservedException.class)
    public ResponseEntity<ApiErrorResponse> handleSeatAlreadyReservedException(SeatAlreadyReservedException ex) {
        ApiErrorResponse response = new ApiErrorResponse(ex.getMessage(), ex.getErrorCode(),false);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGenericException(Exception ex) {
        ApiErrorResponse response = new ApiErrorResponse("Internal Server Error",90000, false);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}