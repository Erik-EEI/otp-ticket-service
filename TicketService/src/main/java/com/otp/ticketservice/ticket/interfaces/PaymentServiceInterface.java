package com.otp.ticketservice.ticket.interfaces;

import com.otp.ticketservice.api.dto.PaymentDataDTO;
import com.otp.ticketservice.ticket.dto.payment.PaymentResponseDTO;

public interface PaymentServiceInterface {
    PaymentResponseDTO payForReservation(PaymentDataDTO paymentData);
}
