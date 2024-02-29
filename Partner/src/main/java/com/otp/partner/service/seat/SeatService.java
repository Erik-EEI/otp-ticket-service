package com.otp.partner.service.seat;

import com.otp.partner.entity.Seat;
import com.otp.partner.exception.SeatNotFoundException;
import com.otp.partner.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SeatService {

    private final SeatRepository seatRepository;

    @Autowired
    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public Seat findSeatById(Long seatId) {
        return seatRepository.findById(seatId)
                .orElseThrow(SeatNotFoundException::new);
    }
    @Transactional //TODO research further transactional
    public void updateSeatReserved(Long seatId, boolean reserved) {
        Seat seat = this.findSeatById(seatId);
        seat.setReserved(reserved);

        seatRepository.save(seat);
    }
}

