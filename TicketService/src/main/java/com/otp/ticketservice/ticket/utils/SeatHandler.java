package com.otp.ticketservice.ticket.utils;

import com.otp.ticketservice.ticket.dto.single_event_with_seats.EventSeatsDTO;
import com.otp.ticketservice.ticket.dto.single_event_with_seats.SeatDTO;
import com.otp.ticketservice.ticket.exceptions.CanNotReserveOccupiedSeatException;
import com.otp.ticketservice.ticket.exceptions.SeatDoesNotExistException;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@UtilityClass
public class SeatHandler {
    private final Logger LOGGER = LoggerFactory.getLogger("[TICKET - SEAT HANDLER UTIL]");

    public static SeatDTO getSeat(List<SeatDTO> seatList, Long seatId){
        return seatList
                .stream()
                .filter(seatDTO-> seatDTO.id() == seatId)
                .findFirst()
                .orElseThrow(SeatDoesNotExistException::new);
    }

    public static void validateIfSeatIsReservable( SeatDTO seat ){
        if (seat.reserved()) throw new CanNotReserveOccupiedSeatException();
        LOGGER.info("✔ - VALID -- Seat is reservable");
    }
}
