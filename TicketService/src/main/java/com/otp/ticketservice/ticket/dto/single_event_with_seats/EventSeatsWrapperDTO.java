package com.otp.ticketservice.ticket.dto.single_event_with_seats;

public record EventSeatsWrapperDTO(
        EventSeatsDTO data,
        boolean success
){}
