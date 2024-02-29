package com.otp.partner.service.reservation;

import com.otp.partner.entity.Event;
import com.otp.partner.entity.Reservation;
import com.otp.partner.entity.Seat;
import com.otp.partner.exception.ReservationNotFoundException;
import com.otp.partner.exception.SeatAlreadyReservedException;
import com.otp.partner.exception.SeatNotFoundException;
import com.otp.partner.repository.ReservationRepository;
import com.otp.partner.service.event.EventService;
import com.otp.partner.service.seat.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final SeatService seatService;
    private final EventService eventService;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, SeatService seatService, EventService eventService) {
        this.reservationRepository = reservationRepository;
        this.eventService = eventService;
        this.seatService = seatService;
    }

    public Long createReservation(long eventId, long seatId) {
        Event event = eventService.getEventById(eventId);
        Seat seat = seatService.findSeatById(seatId);

        if(!seat.getEvent().equals(event)) throw new SeatNotFoundException();
        if(seat.isReserved()) throw new SeatAlreadyReservedException();

        seatService.updateSeatReserved(seat.getId(),true);

        Reservation reservation = new Reservation();
        reservation.setEvent(event);
        reservation.setSeat(seat);

        Reservation savedReservation = reservationRepository.save(reservation);
        return savedReservation.getId();
    }

    public Reservation getReservationById(Long reservationId) {

        return reservationRepository.findById(reservationId)
                .orElseThrow(ReservationNotFoundException::new);
    }

    public void cancelReservation(Long reservationId) {
        Reservation reservation = this.getReservationById(reservationId);

        seatService.updateSeatReserved(reservation.getSeat().getId(),false);

        reservationRepository.delete(reservation);
    }
}
