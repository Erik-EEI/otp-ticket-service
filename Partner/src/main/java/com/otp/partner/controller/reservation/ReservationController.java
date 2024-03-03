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
    public ResponseEntity<ApiReservationResponse> createReservation(@RequestBody ReservationRequestDTO reservationData) {
        LOGGER.info("POST REQUEST at /reserve endpoint");
        Long eventId = reservationData.eventId();
        Long seatId = reservationData.seatId();
        Long reservationId = reservationService.createReservation(eventId, seatId);

        return new ResponseEntity<>(new ApiReservationResponse(reservationId, true), HttpStatus.OK);
    }

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
    public ResponseEntity<ApiResponseDTO> getReservationById(@RequestParam Long reservationId) {
        LOGGER.info("GET REQUEST at /reservation endpoint");
        Reservation reservation = reservationService.getReservationById(reservationId);
        ReservationDTO reservationDTO = ReservationMapper.mapToReservationDTO( reservation );

        return new ResponseEntity<>(new ApiResponseDTO(reservationDTO, true), HttpStatus.OK);
    }

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
        LOGGER.info("DELETE REQUEST at /reservation endpoint");
        reservationService.cancelReservation(reservationId);
        return new ResponseEntity<>(new ApiResponseDTO("Reservation with ID " + reservationId + " cancelled", true), HttpStatus.OK);
    }
}
