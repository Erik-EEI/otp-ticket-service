package com.otp.partner.controller.reservation;

import com.otp.partner.dto.ReservationDTO;
import com.otp.partner.dto.request.ReservationRequestDTO;
import com.otp.partner.dto.response.ApiResponse;
import com.otp.partner.entity.Reservation;
import com.otp.partner.service.reservation.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/reserve")
    public ResponseEntity<ApiResponse> createReservation(@RequestBody ReservationRequestDTO reservationData) {
        Long eventId = reservationData.eventId();
        Long seatId = reservationData.seatId();
        Long reservationId = reservationService.createReservation(eventId, seatId);

        return new ResponseEntity<>(new ApiResponse(reservationId, true), HttpStatus.OK);
    }

    @GetMapping("/reservation")
    public ResponseEntity<ApiResponse> getReservationById(@RequestParam Long reservationId) {
        Reservation reservation = reservationService.getReservationById(reservationId);

        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setSeatId(reservation.getSeat().getId());
        reservationDTO.setEventId(reservation.getEvent().getId());
        reservationDTO.setCreatedAt(reservation.getCreatedAt());

        return new ResponseEntity<>(new ApiResponse(reservationDTO, true), HttpStatus.OK);
    }

    @DeleteMapping("/reservation")
    public ResponseEntity<ApiResponse> cancelReservation(@RequestParam Long reservationId) {
        reservationService.cancelReservation(reservationId);
        return new ResponseEntity<>(new ApiResponse("Reservation with ID " + reservationId + " cancelled", true), HttpStatus.OK);
    }
}
