package com.otp.partner.dto;

/**
 * Data Transfer Object (DTO) representing basic event information.
 */
public record EventDTO(
        Long eventId,
        String title,
        String location,
        String startTimeStamp,
        String endTimeStamp
) {}
