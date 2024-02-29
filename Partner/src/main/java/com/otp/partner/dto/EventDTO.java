package com.otp.partner.dto;

import java.sql.Timestamp;
import java.util.Date;

public record EventDTO(
        Long eventId,
        String title,
        String location,
        String startTimeStamp,
        String endTimeStamp
) {}
