package com.otp.ticketservice.ticket.dto.event_list;

import lombok.Data;

import java.sql.Timestamp;

public record EventDTO(
        Long eventId,
        String title,
        String location,
        String startTimeStamp,
        String endTimeStamp
) { }
