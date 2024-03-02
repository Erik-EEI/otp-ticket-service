package com.otp.ticketservice.ticket.dto.event_list;

import java.util.List;

import lombok.Data;

public record EventListDTO(
        List<EventDTO> data,
        boolean success
) {}
