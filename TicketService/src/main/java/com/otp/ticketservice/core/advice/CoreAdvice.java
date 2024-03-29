package com.otp.ticketservice.core.advice;

import com.otp.ticketservice.api.dto.response.ApiErrorResponse;
import com.otp.ticketservice.core.exception.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Advice class for handling exceptions related to the core module.
 */
@RestControllerAdvice
public class CoreAdvice {
    private final Logger LOGGER = LoggerFactory.getLogger("[CORE MODULE]");

    /**
     * Handles the CardNotMatchUserException.
     * @param ex The CardNotMatchUserException instance.
     * @return ResponseEntity containing the error response.
     */
    @ExceptionHandler(CardNotMatchUserException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ApiResponse(responseCode = "401", description = "Card does not match to user",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
    public ResponseEntity<ApiErrorResponse> handleCardNotMatchUserException(CardNotMatchUserException ex) {
        LOGGER.error(String.format("❌ - / %s / - %s",ex.getErrorCode(),ex.getMessage()));
        ApiErrorResponse response = new ApiErrorResponse(ex.getMessage(), ex.getErrorCode(),false);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    /**
     * Handles the NotEnoughAmountOnCardException.
     * @param ex The NotEnoughAmountOnCardException instance.
     * @return ResponseEntity containing the error response.
     */
    @ExceptionHandler(NotEnoughAmountOnCardException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ApiResponse(responseCode = "401", description = "Not enough amount on card",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
    public ResponseEntity<ApiErrorResponse> handleNotEnoughAmountOnCardException(NotEnoughAmountOnCardException ex) {
        LOGGER.error(String.format("❌ - / %s / - %s",ex.getErrorCode(),ex.getMessage()));
        ApiErrorResponse response = new ApiErrorResponse(ex.getMessage(), ex.getErrorCode(),false);
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    /**
     * Handles the TokenCanNotBeDecodedException.
     * @param ex The TokenCanNotBeDecodedException instance.
     * @return ResponseEntity containing the error response.
     */
    @ExceptionHandler(TokenCanNotBeDecodedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ApiResponse(responseCode = "401", description = "Token can not be decoded",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
    public ResponseEntity<ApiErrorResponse> handleTokenCanNotBeDecodedException(TokenCanNotBeDecodedException ex) {
        LOGGER.error(String.format("❌ - / %s / - %s",ex.getErrorCode(),ex.getMessage()));
        ApiErrorResponse response = new ApiErrorResponse(ex.getMessage(), ex.getErrorCode(),false);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles the TokenNotFoundException.
     * @param ex The TokenNotFoundException instance.
     * @return ResponseEntity containing the error response.
     */
    @ExceptionHandler(TokenNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ApiResponse(responseCode = "401", description = "Token does not belong to any user",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
    public ResponseEntity<ApiErrorResponse> handleTokenNotFoundException(TokenNotFoundException ex) {
        LOGGER.error(String.format("❌ - / %s / - %s",ex.getErrorCode(),ex.getMessage()));
        ApiErrorResponse response = new ApiErrorResponse(ex.getMessage(), ex.getErrorCode(),false);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles the CardNotFoundException.
     * @param ex The CardNotFoundException instance.
     * @return ResponseEntity containing the error response.
     */
    @ExceptionHandler(CardNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ApiResponse(responseCode = "404", description = "Token does not belong to any user",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
    public ResponseEntity<ApiErrorResponse> handleCardNotFoundException(CardNotFoundException ex) {
        LOGGER.error(String.format("❌ - / %s / - %s",ex.getErrorCode(),ex.getMessage()));
        ApiErrorResponse response = new ApiErrorResponse(ex.getMessage(), ex.getErrorCode(),false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles the MissingRequestHeaderException.
     * @param ex The MissingRequestHeaderException instance.
     * @return ResponseEntity containing the error response.
     */
    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ApiResponse(responseCode = "401", description = "Token not found in request header",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
    public ResponseEntity<ApiErrorResponse> handleMissingRequestHeaderException(MissingRequestHeaderException ex) {
        NoTokenInRequestHeaderException customEx = new NoTokenInRequestHeaderException();
        ApiErrorResponse response = new ApiErrorResponse(customEx.getMessage(), customEx.getErrorCode(),false);
        LOGGER.error(String.format("❌ - / %s / - %s",customEx.getErrorCode(),customEx.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
