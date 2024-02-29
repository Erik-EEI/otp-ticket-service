package com.otp.partner.dto;

import java.util.List;

public record DetailedEventDTO(
        Long eventId,
        String title,
        String location,
        String startTimeStamp,
        String endTimeStamp,
        List<SeatDTO> seats
) {}
