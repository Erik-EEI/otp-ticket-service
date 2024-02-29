package com.otp.ticketservice.ticket.utils;

import com.otp.ticketservice.ticket.dto.single_event_with_seats.EventWithSeatsResponseDTO;
import com.otp.ticketservice.ticket.dto.single_event_with_seats.SeatDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SeatHandler {

    public SeatDTO getSeat(EventWithSeatsResponseDTO event, Long seatId){
        return event
                .getData()
                .getSeats()
                .stream()
                .filter(seatDTO-> seatDTO.getId() == seatId)
                .findFirst()
                .orElseThrow(RuntimeException::new); //TODO Replace with custom exception
    }

    public void validateIfSeatIsReservable( SeatDTO seat ){
        if (!seat.isReserved()) throw new RuntimeException(); //TODO Replace with custom exception
    }
}
