package com.otp.partner.controller.reservation;

import com.otp.partner.dto.ReservationDTO;
import com.otp.partner.dto.request.ReservationRequestDTO;
import com.otp.partner.dto.response.ApiErrorResponseDTO;
import com.otp.partner.dto.response.ApiReservationResponse;
import com.otp.partner.dto.response.ApiResponseDTO;
import com.otp.partner.entity.Reservation;
import com.otp.partner.mapper.ReservationMapper;
import com.otp.partner.service.reservation.ReservationService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for handling reservation-related operations.
 */
@RestController
@RequestMapping("/")
@Tag(name = "Reservation Controller", description = "Controller for handling reservation related operations")
public class ReservationController {
    private final ReservationService reservationService;
    private final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    /**
     * Creates a reservation.
     * @param reservationData The reservation request data.
     * @return ResponseEntity containing the reservation response.
     */
    @Operation(summary = "Make a reservation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful reservation",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Invalid api secret or key",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Event or seat not found",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponseDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponseDTO.class))})
    })
    @PostMapping("/reserve")
    public ResponseEntity<ApiReservationResponse> createReservation(
            @Parameter(in = ParameterIn.HEADER, required = true, description = "API Key", example = "partner-key", schema = @Schema(type = "string")) @RequestHeader("x-api-key") String apiKey,
            @Parameter(in = ParameterIn.HEADER, required = true, description = "API Secret", example = "partner-secret", schema = @Schema(type = "string")) @RequestHeader("x-api-secret") String apiSecret,
            @RequestBody ReservationRequestDTO reservationData
    ) {
        LOGGER.info(String.format(" » POST REQUEST at /reserve endpoint | EventId: %s , SeatId: %s", reservationData.eventId(),reservationData.seatId()));
        Long eventId = reservationData.eventId();
        Long seatId = reservationData.seatId();
        Long reservationId = reservationService.createReservation(eventId, seatId);

        LOGGER.info(String.format(" « RESPONSE Sent to POST REQUEST at /reserve endpoint | EventId: %s , SeatId: %s", reservationData.eventId(),reservationData.seatId()));
        return new ResponseEntity<>(new ApiReservationResponse(reservationId, true), HttpStatus.OK);
    }

    /**
     * Retrieves a reservation by ID.
     * @param reservationId The ID of the reservation to retrieve.
     * @return ResponseEntity containing the reservation information.
     */
    @Operation(summary = "Retrieve a reservation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation data retrieved",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Invalid api secret or key",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Reservation not found",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponseDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponseDTO.class))})
    })
    @GetMapping("/reservation")
    public ResponseEntity<ApiResponseDTO> getReservationById(
            @Parameter(in = ParameterIn.HEADER, required = true, description = "API Key", example = "partner-key", schema = @Schema(type = "string")) @RequestHeader("x-api-key") String apiKey,
            @Parameter(in = ParameterIn.HEADER, required = true, description = "API Secret", example = "partner-secret", schema = @Schema(type = "string")) @RequestHeader("x-api-secret") String apiSecret,
            @RequestParam Long reservationId
    ) {
        LOGGER.info(String.format(" » GET REQUEST at /reserve endpoint | reservation ID: %s",reservationId));
        Reservation reservation = reservationService.getReservationById(reservationId);
        ReservationDTO reservationDTO = ReservationMapper.mapToReservationDTO( reservation );

        LOGGER.info(String.format(" « RESPONSE Sent to GET REQUEST at /reserve endpoint | reservation ID: %s",reservationId));
        return new ResponseEntity<>(new ApiResponseDTO(reservationDTO, true), HttpStatus.OK);
    }

    /**
     * Cancels a reservation.
     * @param reservationId The ID of the reservation to cancel.
     * @return ResponseEntity containing the cancellation response.
     */
    @Operation(summary = "Cancel a reservation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation successfully cancelled",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Invalid api secret or key",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Reservation not found",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponseDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponseDTO.class))})
    })
    @DeleteMapping("/reservation")
    public ResponseEntity<ApiResponseDTO> cancelReservation(@RequestParam Long reservationId) {
        LOGGER.info(String.format(" » DELETE REQUEST at /reserve endpoint | reservation ID: %s",reservationId));
        reservationService.cancelReservation(reservationId);
        LOGGER.info(String.format(" « RESPONSE Sent to DELETE REQUEST at /reserve endpoint | reservation ID: %s",reservationId));
        return new ResponseEntity<>(new ApiResponseDTO("Reservation with ID " + reservationId + " cancelled", true), HttpStatus.OK);
    }
}
