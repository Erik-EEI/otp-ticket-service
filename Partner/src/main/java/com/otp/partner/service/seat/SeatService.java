package com.otp.partner.service.seat;

import com.otp.partner.entity.Seat;
import com.otp.partner.exception.SeatNotFoundException;
import com.otp.partner.repository.SeatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SeatService {

    private final SeatRepository seatRepository;
    private final Logger LOGGER = LoggerFactory.getLogger("[SEAT SERVICE]");

    @Autowired
    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public Seat findSeatById(Long seatId) {
        LOGGER.info(String.format("► \uD83D\uDCC2 - Getting Seat with ID %s from database",seatId));
        return seatRepository.findById(seatId)
                .orElseThrow(SeatNotFoundException::new);
    }

    public void updateSeatReserved(Long seatId, boolean reserved) {
        Seat seat = this.findSeatById(seatId);
        seat.setReserved(reserved);

        seatRepository.save(seat);
        LOGGER.info(String.format("✔️ - Updated seat %s to reserved: %s",seatId,reserved));
    }
}

