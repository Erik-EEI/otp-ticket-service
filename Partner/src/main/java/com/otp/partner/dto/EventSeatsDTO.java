package com.otp.partner.dto;

import java.util.List;

/**
 * Data Transfer Object (DTO) representing response format for simple event request.
 */
public record EventSeatsDTO(
        Long eventId,
        List<SeatDTO> seats
) {}
