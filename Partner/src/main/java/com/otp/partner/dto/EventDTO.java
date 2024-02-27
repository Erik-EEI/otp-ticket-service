package com.otp.partner.dto;

public record EventDTO(
        Long eventId,
        String title,
        String location,
        Long startTimeStamp,
        Long endTimeStamp
) {}
