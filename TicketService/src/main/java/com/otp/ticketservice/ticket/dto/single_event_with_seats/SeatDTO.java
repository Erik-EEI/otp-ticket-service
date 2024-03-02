package com.otp.ticketservice.ticket.dto.single_event_with_seats;

public record SeatDTO(
        long id,
        String seatName,
        int price,
        String currency,
        boolean reserved
){}
