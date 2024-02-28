package com.otp.ticketservice.ticket.service;

import com.otp.ticketservice.api.dto.PaymentDataDTO;
import com.otp.ticketservice.core.interfaces.CoreServiceInterface;
import com.otp.ticketservice.ticket.dto.PaymentResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final CoreServiceInterface coreService;

    public PaymentService(CoreServiceInterface coreService) {
        this.coreService = coreService;
    }

    public PaymentResponseDTO payForReservation(PaymentDataDTO paymentData){
        return new PaymentResponseDTO(); // TODO For testing
    }
}
