package com.otp.ticketservice.core.interfaces;

import com.otp.ticketservice.core.entity.UserBankCard;

public interface CardServiceInterface {
    UserBankCard getCardById(String cardId);
    void processPayment(String cardId, double amount);
    void updateAmount(String cardId, double amount);
}
