package com.otp.partner.controller.advice;

import com.otp.partner.controller.reservation.ReservationController;
import com.otp.partner.dto.response.ApiErrorResponseDTO;
import com.otp.partner.exception.EventNotFoundException;
import com.otp.partner.exception.ReservationNotFoundException;
import com.otp.partner.exception.SeatAlreadyReservedException;
import com.otp.partner.exception.SeatNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.otp.partner.controller")
public class ControllerAdvice {
    private final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<ApiErrorResponseDTO> handleEventNotFoundException(EventNotFoundException ex) {
        LOGGER.error(String.format("❌ - / %s / - %s",ex.getErrorCode(),ex.getMessage()));
        ApiErrorResponseDTO response = new ApiErrorResponseDTO(ex.getMessage(), ex.getErrorCode(),false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<ApiErrorResponseDTO> handleReservationNotFoundException(ReservationNotFoundException ex) {
        LOGGER.error(String.format("❌ - / %s / - %s",ex.getErrorCode(),ex.getMessage()));
        ApiErrorResponseDTO response = new ApiErrorResponseDTO(ex.getMessage(), ex.getErrorCode(),false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SeatNotFoundException.class)
    public ResponseEntity<ApiErrorResponseDTO> handleSeatNotFoundException(SeatNotFoundException ex) {
        LOGGER.error(String.format("❌ - / %s / - %s",ex.getErrorCode(),ex.getMessage()));
        ApiErrorResponseDTO response = new ApiErrorResponseDTO(ex.getMessage(), ex.getErrorCode(),false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(SeatAlreadyReservedException.class)
    public ResponseEntity<ApiErrorResponseDTO> handleSeatAlreadyReservedException(SeatAlreadyReservedException ex) {
        LOGGER.error(String.format("❌ - / %s / - %s",ex.getErrorCode(),ex.getMessage()));
        ApiErrorResponseDTO response = new ApiErrorResponseDTO(ex.getMessage(), ex.getErrorCode(),false);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponseDTO> handleGenericException(Exception ex) {
        LOGGER.error(String.format("❌ - / %s / - %s","Internal Server Error",ex.getMessage()));
        ApiErrorResponseDTO response = new ApiErrorResponseDTO("Internal Server Error",90000, false);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
