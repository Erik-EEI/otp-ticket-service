package com.otp.partner.service.reservation;

import com.otp.partner.entity.Event;
import com.otp.partner.entity.Reservation;
import com.otp.partner.entity.Seat;
import com.otp.partner.exception.ReservationNotFoundException;
import com.otp.partner.exception.SeatAlreadyReservedException;
import com.otp.partner.exception.SeatNotFoundException;
import com.otp.partner.interfaces.EventServiceInterface;
import com.otp.partner.interfaces.ReservationServiceInterface;
import com.otp.partner.interfaces.SeatServiceInterface;
import com.otp.partner.repository.ReservationRepository;
import com.otp.partner.service.event.EventService;
import com.otp.partner.service.seat.SeatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService implements ReservationServiceInterface {

    private final ReservationRepository reservationRepository;
    private final SeatServiceInterface seatService;
    private final EventServiceInterface eventService;
    private final Logger LOGGER = LoggerFactory.getLogger("[RESERVATION SERVICE]");

    /**
     * Service class responsible for handling reservation-related operations.
     */
    @Autowired
    public ReservationService(ReservationRepository reservationRepository, SeatServiceInterface seatService, EventServiceInterface eventService) {
        this.reservationRepository = reservationRepository;
        this.eventService = eventService;
        this.seatService = seatService;
    }

    /**
     * Creates a new reservation for the specified event and seat.
     *
     * @param eventId The ID of the event for which the reservation is being made.
     * @param seatId  The ID of the seat to be reserved.
     * @return The ID of the created reservation.
     * @throws SeatNotFoundException        If the specified seat does not exist.
     * @throws SeatAlreadyReservedException If the specified seat is already reserved.
     */
    @Override
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
        LOGGER.info(String.format("✔️ - Saved reservation with ID %s into database",savedReservation.getId()));
        return savedReservation.getId();
    }

    /**
     * Retrieves a reservation by its ID.
     *
     * @param reservationId The ID of the reservation to retrieve.
     * @return The reservation with the specified ID.
     * @throws ReservationNotFoundException If the specified reservation does not exist.
     */
    @Override
    public Reservation getReservationById(Long reservationId) {

        LOGGER.info(String.format("► \uD83D\uDCC2 - Getting reservation with ID %s from database",reservationId));
        return reservationRepository.findById(reservationId)
                .orElseThrow(ReservationNotFoundException::new);
    }

    /**
     * Cancels a reservation.
     *
     * @param reservationId The ID of the reservation to cancel.
     */
    @Override
    public void cancelReservation(Long reservationId) {
        Reservation reservation = this.getReservationById(reservationId);

        seatService.updateSeatReserved(reservation.getSeat().getId(),false);

        reservationRepository.delete(reservation);
        LOGGER.info(String.format("✔️ - Reservation cancelled with ID %s from database",reservationId));
    }
}
