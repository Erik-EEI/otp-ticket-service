package com.otp.partner.service.seat;

import com.otp.partner.entity.Seat;
import com.otp.partner.exception.SeatNotFoundException;
import com.otp.partner.interfaces.SeatServiceInterface;
import com.otp.partner.repository.SeatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing seats.
 */
@Service
public class SeatService implements SeatServiceInterface {

    private final SeatRepository seatRepository;
    private final Logger LOGGER = LoggerFactory.getLogger("[SEAT SERVICE]");

    @Autowired
    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    /**
     * Finds a seat by its ID.
     * @param seatId the ID of the seat to find
     * @return the seat with the specified ID
     * @throws SeatNotFoundException if the seat with the specified ID is not found
     */
    @Override
    public Seat findSeatById(Long seatId) {
        LOGGER.info(String.format("► \uD83D\uDCC2 - Getting Seat with ID %s from database",seatId));
        return seatRepository.findById(seatId)
                .orElseThrow(SeatNotFoundException::new);
    }

    /**
     * Updates the reservation status of a seat.
     * @param seatId the ID of the seat to update
     * @param reserved the new reservation status
     * @throws SeatNotFoundException if the seat with the specified ID is not found
     */
    @Override
    public void updateSeatReserved(Long seatId, boolean reserved) {
        Seat seat = this.findSeatById(seatId);
        seat.setReserved(reserved);

        seatRepository.save(seat);
        LOGGER.info(String.format("✔️ - Updated seat %s to reserved: %s",seatId,reserved));
    }
}

