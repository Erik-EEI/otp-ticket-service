package com.otp.ticketservice.ticket.dto.detailed_event;

/**
 * Data transfer object (DTO) representing a wrapper for detailed event data along with a success flag.
 * This wrapper is necessary for mapping, because it reflects the format of the http response of partner unit
 */
public record DetailedEventWrapperDTO(
        DetailedEventDTO data,
        boolean success
) {
}
