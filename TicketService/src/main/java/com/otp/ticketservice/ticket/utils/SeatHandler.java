package com.otp.ticketservice.ticket.utils;

import com.otp.ticketservice.ticket.dto.single_event_with_seats.SeatDTO;
import com.otp.ticketservice.ticket.exceptions.CanNotReserveOccupiedSeatException;
import com.otp.ticketservice.ticket.exceptions.SeatDoesNotExistException;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Utility class for handling seat-related operations.
 */
@UtilityClass
public class SeatHandler {
    private final Logger LOGGER = LoggerFactory.getLogger("[TICKET - SEAT HANDLER UTIL]");

    /**
     * Retrieves the seat with the specified ID from the list of seats.
     * @param seatList the list of seats to search
     * @param seatId the ID of the seat to retrieve
     * @return the seat with the specified ID
     * @throws SeatDoesNotExistException if the seat with the specified ID does not exist
     */
    public static SeatDTO getSeat(List<SeatDTO> seatList, Long seatId){
        return seatList
                .stream()
                .filter(seatDTO-> seatDTO.id() == seatId)
                .findFirst()
                .orElseThrow(SeatDoesNotExistException::new);
    }

    /**
     * Validates if a seat is reservable.
     * @param seat the seat to validate
     * @throws CanNotReserveOccupiedSeatException if the seat is already reserved
     */
    public static void validateIfSeatIsReservable( SeatDTO seat ){
        if (seat.reserved()) throw new CanNotReserveOccupiedSeatException();
        LOGGER.info("✔ - VALID -- Seat is reservable");
    }
}
