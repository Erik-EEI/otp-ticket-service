package com.otp.ticketservice.ticket.dto;

import lombok.Data;


public record CustomErrorResponseDTO (
        String message,
        int errorCode,
        boolean success
){};
