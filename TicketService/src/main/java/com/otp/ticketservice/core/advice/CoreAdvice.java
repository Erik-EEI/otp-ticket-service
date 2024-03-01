package com.otp.ticketservice.core.advice;

import com.otp.ticketservice.api.controller.ApiController;
import com.otp.ticketservice.api.dto.response.ApiErrorResponse;
import com.otp.ticketservice.core.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CoreAdvice {
    private final Logger LOGGER = LoggerFactory.getLogger("[CORE MODULE]");
    @ExceptionHandler(CardNotMatchUserException.class)
    public ResponseEntity<ApiErrorResponse> handleEventNotFoundException(CardNotMatchUserException ex) {
        LOGGER.error(String.format("❌ - / %s / - %s",ex.getErrorCode(),ex.getMessage()));
        ApiErrorResponse response = new ApiErrorResponse(ex.getMessage(), ex.getErrorCode(),false);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(NotEnoughAmountOnCardException.class)
    public ResponseEntity<ApiErrorResponse> handleEventNotFoundException(NotEnoughAmountOnCardException ex) {
        LOGGER.error(String.format("❌ - / %s / - %s",ex.getErrorCode(),ex.getMessage()));
        ApiErrorResponse response = new ApiErrorResponse(ex.getMessage(), ex.getErrorCode(),false);
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(TokenCanNotBeDecodedException.class)
    public ResponseEntity<ApiErrorResponse> handleEventNotFoundException(TokenCanNotBeDecodedException ex) {
        LOGGER.error(String.format("❌ - / %s / - %s",ex.getErrorCode(),ex.getMessage()));
        ApiErrorResponse response = new ApiErrorResponse(ex.getMessage(), ex.getErrorCode(),false);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleEventNotFoundException(TokenNotFoundException ex) {
        LOGGER.error(String.format("❌ - / %s / - %s",ex.getErrorCode(),ex.getMessage()));
        ApiErrorResponse response = new ApiErrorResponse(ex.getMessage(), ex.getErrorCode(),false);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ApiErrorResponse> handleEventNotFoundException(MissingRequestHeaderException ex) {
        NoTokenInRequestHeaderException customEx = new NoTokenInRequestHeaderException();
        ApiErrorResponse response = new ApiErrorResponse(customEx.getMessage(), customEx.getErrorCode(),false);
        LOGGER.error(String.format("❌ - / %s / - %s",customEx.getErrorCode(),customEx.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
