package com.otp.ticketservice.ticket.dto.single_event_with_seats;

/**
 * Data transfer object (DTO) wrapper for an EventSeatsDTO object.
 * This wrapper is necessary for mapping, because it reflects the format of the http response of partner unit.
 * It includes a boolean flag indicating the success of the operation.
 */
public record EventSeatsWrapperDTO(
        EventSeatsDTO data,
        boolean success
){}
