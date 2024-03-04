package com.otp.ticketservice.ticket.dto.event_list;

/**
 * Data transfer object (DTO) representing an event.
 */
public record EventDTO(
        Long eventId,
        String title,
        String location,
        String startTimeStamp,
        String endTimeStamp
) { }
