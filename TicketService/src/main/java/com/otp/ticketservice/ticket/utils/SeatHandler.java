package com.otp.ticketservice.ticket.utils;

import com.otp.ticketservice.ticket.dto.single_event_with_seats.EventSeatsDTO;
import com.otp.ticketservice.ticket.dto.single_event_with_seats.SeatDTO;
import com.otp.ticketservice.ticket.exceptions.CanNotReserveOccupiedSeatException;
import com.otp.ticketservice.ticket.exceptions.SeatDoesNotExistException;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class SeatHandler {

    public static SeatDTO getSeat(List<SeatDTO> seatList, Long seatId){
        return seatList
                .stream()
                .filter(seatDTO-> seatDTO.getId() == seatId)
                .findFirst()
                .orElseThrow(SeatDoesNotExistException::new);
    }

    public static void validateIfSeatIsReservable( SeatDTO seat ){
        if (seat.isReserved()) throw new CanNotReserveOccupiedSeatException();
    }
}
