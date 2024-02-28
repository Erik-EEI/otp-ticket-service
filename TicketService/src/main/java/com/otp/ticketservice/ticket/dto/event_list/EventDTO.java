package com.otp.ticketservice.ticket.dto.event_list;

import lombok.Data;

@Data //TODO Convert to record
public class EventDTO {
    private Long eventId;
    private String title;
    private String location;
    private Long startTimeStamp;
    private Long endTimeStamp;
}
