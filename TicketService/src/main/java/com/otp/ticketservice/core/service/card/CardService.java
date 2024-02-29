package com.otp.ticketservice.core.service.card;

import com.otp.ticketservice.core.entity.UserBankCard;
import com.otp.ticketservice.core.exception.NotEnoughAmountOnCardException;
import com.otp.ticketservice.core.interfaces.CardServiceInterface;
import com.otp.ticketservice.core.repository.UserBankCardRepository;
import org.springframework.stereotype.Service;

@Service
public class CardService implements CardServiceInterface {
    private final UserBankCardRepository userBankCardRepository;

    public CardService(UserBankCardRepository userBankCardRepository) {
        this.userBankCardRepository = userBankCardRepository;
    }

    @Override
    public UserBankCard getCardById(String cardId){
        return userBankCardRepository.findUserBankCardByCardId(cardId)
                .orElseThrow(RuntimeException::new); // TODO Replace with custom exception
    }

    @Override
    public boolean checkIfAmountIsAvailable(String cardId, double amount){
        UserBankCard card = userBankCardRepository.findUserBankCardByCardId(cardId)
                .orElseThrow(NotEnoughAmountOnCardException::new);

        return card.getAmount() >= amount;
    }
}
