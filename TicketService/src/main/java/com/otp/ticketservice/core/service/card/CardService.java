package com.otp.ticketservice.core.service.card;

import com.otp.ticketservice.core.entity.UserBankCard;
import com.otp.ticketservice.core.repository.UserBankCardRepository;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    private final UserBankCardRepository userBankCardRepository;

    public CardService(UserBankCardRepository userBankCardRepository) {
        this.userBankCardRepository = userBankCardRepository;
    }

    public UserBankCard getCardById(Long cardId){
        return userBankCardRepository.findById(cardId)
                .orElseThrow(RuntimeException::new); // TODO Replace with custom exception
    }
    public UserBankCard getCardForUser(Long userId){
        return userBankCardRepository.findUserBankCardByUserId(userId)
                .orElseThrow(RuntimeException::new); // TODO Replace with custom exception
    }

    public boolean checkIfAmountIsAvailable(Long cardId, double amount){
        UserBankCard card = userBankCardRepository.findById(cardId)
                .orElseThrow(RuntimeException::new); // TODO Replace with custom exception

        return card.getAmount() >= amount;
    }
}
