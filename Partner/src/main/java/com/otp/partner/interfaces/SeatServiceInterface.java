package com.otp.partner.interfaces;

import com.otp.partner.entity.Seat;

public interface SeatServiceInterface {
    Seat findSeatById(Long seatId);
    void updateSeatReserved(Long seatId, boolean reserved);
}
