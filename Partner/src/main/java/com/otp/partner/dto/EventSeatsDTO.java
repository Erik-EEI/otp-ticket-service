package com.otp.partner.dto;

import java.util.List;

public record EventSeatsDTO(
        Long eventId,
        List<SeatDTO> seats
) {}
