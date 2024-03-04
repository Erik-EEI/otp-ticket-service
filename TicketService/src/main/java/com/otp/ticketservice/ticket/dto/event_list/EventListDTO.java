package com.otp.ticketservice.ticket.dto.event_list;

import java.util.List;

/**
 * Data transfer object (DTO) representing a list of events.
 * This wrapper is necessary for mapping, because it reflects the format of the http response of partner unit
 */
public record EventListDTO(
        List<EventDTO> data,
        boolean success
) {}
