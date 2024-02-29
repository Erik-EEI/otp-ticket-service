package com.otp.ticketservice.ticket.dto.detailed_event;

import com.otp.ticketservice.ticket.dto.single_event_with_seats.SeatDTO;

import java.util.List;

public record DetailedEventDTO(
        Long eventId,
        String title,
        String location,
        String startTimeStamp,
        String endTimeStamp,
        List<SeatDTO> seats
) { }
