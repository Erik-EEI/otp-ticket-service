package com.otp.ticketservice.core.interfaces;

import com.otp.ticketservice.core.entity.UserBankCard;

public interface CardServiceInterface {
    UserBankCard getCardById(String cardId);
    void checkIfAmountIsAvailable(String cardId, double amount);
}
