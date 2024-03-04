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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationServiceTest {
    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private SeatService seatService;

    @Mock
    private EventService eventService;

    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.reservationService = new ReservationService(reservationRepository,seatService,eventService);
    }

    @Test
    void testCreateReservation() {
        long eventId = 1L;
        long seatId = 1L;
        Event event = new Event();
        Seat seat = new Seat();
        seat.setEvent(event);
        when(eventService.getEventById(eventId)).thenReturn(event);
        when(seatService.findSeatById(seatId)).thenReturn(seat);
        when(reservationRepository.save(any(Reservation.class))).thenAnswer(invocation -> {
            Reservation savedReservation = invocation.getArgument(0);
            savedReservation.setId(1L);
            return savedReservation;
        });

        Long reservationId = reservationService.createReservation(eventId, seatId);

        assertNotNull(reservationId);
        verify(eventService, times(1)).getEventById(eventId);
        verify(seatService, times(1)).findSeatById(seatId);
        verify(reservationRepository, times(1)).save(any(Reservation.class));
    }

    @Test
    void testCreateReservationSeatNotFound() {
        long eventId = 1L;
        long seatId = 1L;
        Event event = new Event();
        event.setId(eventId);
        when(eventService.getEventById(eventId)).thenReturn(event);
        when(seatService.findSeatById(seatId)).thenThrow(SeatNotFoundException.class);

        assertThrows(SeatNotFoundException.class, () -> reservationService.createReservation(eventId, seatId));
        verify(eventService, times(1)).getEventById(eventId);
        verify(seatService, times(1)).findSeatById(seatId);
        verify(reservationRepository, never()).save(any(Reservation.class));
    }

    @Test
    void testCreateReservationSeatAlreadyReserved() {
        long eventId = 1L;
        long seatId = 1L;
        Event event = new Event();
        event.setId(eventId);
        Seat seat = new Seat();
        seat.setId(seatId);
        seat.setReserved(true);
        seat.setEvent(event);
        when(eventService.getEventById(eventId)).thenReturn(event);
        when(seatService.findSeatById(seatId)).thenReturn(seat);

        assertThrows(SeatAlreadyReservedException.class, () -> reservationService.createReservation(eventId, seatId));
        verify(eventService, times(1)).getEventById(eventId);
        verify(seatService, times(1)).findSeatById(seatId);
        verify(reservationRepository, never()).save(any(Reservation.class));
    }

    @Test
    void testGetReservationById() {
        long reservationId = 1L;
        Reservation reservation = new Reservation();
        reservation.setId(reservationId);
        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservation));

        Reservation retrievedReservation = reservationService.getReservationById(reservationId);

        assertNotNull(retrievedReservation);
        assertEquals(reservationId, retrievedReservation.getId());
        verify(reservationRepository, times(1)).findById(reservationId);
    }

    @Test
    void testGetReservationByIdNotFound() {
        long reservationId = 1L;
        when(reservationRepository.findById(reservationId)).thenReturn(Optional.empty());

        assertThrows(ReservationNotFoundException.class, () -> reservationService.getReservationById(reservationId));
        verify(reservationRepository, times(1)).findById(reservationId);
    }

    @Test
    void testCancelReservation() {
        long reservationId = 1L;
        Reservation reservation = new Reservation();
        reservation.setId(reservationId);
        Seat seat = new Seat();
        seat.setId(1L);
        reservation.setSeat(seat);
        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservation));

        reservationService.cancelReservation(reservationId);

        verify(reservationRepository, times(1)).findById(reservationId);
        verify(seatService, times(1)).updateSeatReserved(seat.getId(), false);
        verify(reservationRepository, times(1)).delete(reservation);
    }

    @Test
    void testCancelReservationNotFound() {
        long reservationId = 1L;
        when(reservationRepository.findById(reservationId)).thenReturn(Optional.empty());

        assertThrows(ReservationNotFoundException.class, () -> reservationService.cancelReservation(reservationId));
        verify(reservationRepository, times(1)).findById(reservationId);
        verify(seatService, never()).updateSeatReserved(anyLong(), anyBoolean());
        verify(reservationRepository, never()).delete(any(Reservation.class));
    }

}