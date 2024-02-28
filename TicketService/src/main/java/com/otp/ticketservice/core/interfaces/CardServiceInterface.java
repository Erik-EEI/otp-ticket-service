package com.otp.ticketservice.core.interfaces;

import com.otp.ticketservice.core.entity.UserBankCard;

public interface CardServiceInterface {
    UserBankCard getCardById(Long cardId);
    boolean checkIfAmountIsAvailable(Long cardId, double amount);
}
