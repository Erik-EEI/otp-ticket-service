package com.otp.partner.controller.reservation;

import com.otp.partner.controller.event.EventController;
import com.otp.partner.dto.ReservationDTO;
import com.otp.partner.dto.request.ReservationRequestDTO;
import com.otp.partner.dto.response.ApiReservationResponse;
import com.otp.partner.dto.response.ApiResponse;
import com.otp.partner.entity.Reservation;
import com.otp.partner.mapper.ReservationMapper;
import com.otp.partner.service.reservation.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ReservationController {
    private final ReservationService reservationService;
    private final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/reserve")
    public ResponseEntity<ApiReservationResponse> createReservation(@RequestBody ReservationRequestDTO reservationData) {
        LOGGER.info("POST REQUEST at /reserve endpoint");
        Long eventId = reservationData.eventId();
        Long seatId = reservationData.seatId();
        Long reservationId = reservationService.createReservation(eventId, seatId);

        return new ResponseEntity<>(new ApiReservationResponse(reservationId, true), HttpStatus.OK);
    }

    @GetMapping("/reservation")
    public ResponseEntity<ApiResponse> getReservationById(@RequestParam Long reservationId) {
        LOGGER.info("GET REQUEST at /reservation endpoint");
        Reservation reservation = reservationService.getReservationById(reservationId);
        ReservationDTO reservationDTO = ReservationMapper.mapToReservationDTO( reservation );

        return new ResponseEntity<>(new ApiResponse(reservationDTO, true), HttpStatus.OK);
    }

    @DeleteMapping("/reservation")
    public ResponseEntity<ApiResponse> cancelReservation(@RequestParam Long reservationId) {
        LOGGER.info("DELETE REQUEST at /reservation endpoint");
        reservationService.cancelReservation(reservationId);
        return new ResponseEntity<>(new ApiResponse("Reservation with ID " + reservationId + " cancelled", true), HttpStatus.OK);
    }
}
