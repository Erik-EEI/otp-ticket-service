package com.otp.ticketservice.ticket.utils;

import com.otp.ticketservice.ticket.dto.single_event_with_seats.EventSeatsDTO;
import com.otp.ticketservice.ticket.dto.single_event_with_seats.SeatDTO;
import com.otp.ticketservice.ticket.exceptions.CanNotReserveOccupiedSeatException;
import com.otp.ticketservice.ticket.exceptions.SeatDoesNotExistException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SeatHandler {

    public static SeatDTO getSeat(EventSeatsDTO event, Long seatId){
        return event
                .getSeats()
                .stream()
                .filter(seatDTO-> seatDTO.getId() == seatId)
                .findFirst()
                .orElseThrow(SeatDoesNotExistException::new);
    }

    public static void validateIfSeatIsReservable( SeatDTO seat ){
        if (seat.isReserved()) throw new CanNotReserveOccupiedSeatException();
    }
}
