package com.otp.ticketservice.ticket.dto.single_event_with_seats;

import lombok.Data;

@Data
public class SeatDTO {
    private long id;
    private String seatName;
    private int price;
    private String currency;
    private boolean reserved;
}
