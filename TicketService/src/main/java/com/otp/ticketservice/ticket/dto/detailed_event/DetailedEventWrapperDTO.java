package com.otp.ticketservice.ticket.dto.detailed_event;

public record DetailedEventWrapperDTO(
        DetailedEventDTO data,
        boolean success
) {
}
