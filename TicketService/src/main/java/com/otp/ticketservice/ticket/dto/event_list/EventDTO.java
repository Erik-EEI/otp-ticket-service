package com.otp.ticketservice.ticket.dto.event_list;

import lombok.Data;

import java.sql.Timestamp;

@Data //TODO Convert to record
public class EventDTO {
    private Long eventId;
    private String title;
    private String location;
    private String startTimeStamp;
    private String endTimeStamp;
}
