package com.otp.partner.dto;

import java.util.List;

/**
 * Data Transfer Object (DTO) representing a response for a detailed event request .
 */
public record DetailedEventDTO(
        Long eventId,
        String title,
        String location,
        String startTimeStamp,
        String endTimeStamp,
        List<SeatDTO> seats
) {}
