package com.otp.ticketservice.core.advice;

import com.otp.ticketservice.api.dto.response.ApiErrorResponse;
import com.otp.ticketservice.core.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CoreAdvice {
    @ExceptionHandler(CardNotMatchUserException.class)
    public ResponseEntity<ApiErrorResponse> handleEventNotFoundException(CardNotMatchUserException ex) {
        ApiErrorResponse response = new ApiErrorResponse(ex.getMessage(), ex.getErrorCode(),false);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(NotEnoughAmountOnCardException.class)
    public ResponseEntity<ApiErrorResponse> handleEventNotFoundException(NotEnoughAmountOnCardException ex) {
        ApiErrorResponse response = new ApiErrorResponse(ex.getMessage(), ex.getErrorCode(),false);
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(TokenCanNotBeDecodedException.class)
    public ResponseEntity<ApiErrorResponse> handleEventNotFoundException(TokenCanNotBeDecodedException ex) {
        ApiErrorResponse response = new ApiErrorResponse(ex.getMessage(), ex.getErrorCode(),false);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleEventNotFoundException(TokenNotFoundException ex) {
        ApiErrorResponse response = new ApiErrorResponse(ex.getMessage(), ex.getErrorCode(),false);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ApiErrorResponse> handleEventNotFoundException(MissingRequestHeaderException ex) {
        NoTokenInRequestHeaderException customEx = new NoTokenInRequestHeaderException();
        ApiErrorResponse response = new ApiErrorResponse(customEx.getMessage(), customEx.getErrorCode(),false);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
