package com.otp.partner.interfaces;

import com.otp.partner.entity.Reservation;

public interface ReservationServiceInterface {
    Long createReservation(long eventId, long seatId);
    Reservation getReservationById(Long reservationId);
    void cancelReservation(Long reservationId);
}
