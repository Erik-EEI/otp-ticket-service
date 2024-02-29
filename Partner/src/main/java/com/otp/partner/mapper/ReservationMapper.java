package com.otp.partner.mapper;

import com.otp.partner.dto.ReservationDTO;
import com.otp.partner.entity.Reservation;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ReservationMapper {

    public static ReservationDTO mapToReservationDTO(Reservation reservation){
        return new ReservationDTO(
        reservation.getId(),
        reservation.getEvent().getId(),
        reservation.getSeat().getId(),
        reservation.getCreatedAt()
        );
    }
}
