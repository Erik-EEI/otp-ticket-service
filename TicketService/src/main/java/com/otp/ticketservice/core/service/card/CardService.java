package com.otp.ticketservice.core.service.card;

import com.otp.ticketservice.core.entity.UserBankCard;
import com.otp.ticketservice.core.exception.CardNotFoundException;
import com.otp.ticketservice.core.exception.NotEnoughAmountOnCardException;
import com.otp.ticketservice.core.interfaces.CardServiceInterface;
import com.otp.ticketservice.core.repository.UserBankCardRepository;
import org.springframework.stereotype.Service;

/**
 * Service class for managing user bank cards.
 */
@Service
public class CardService implements CardServiceInterface {
    private final UserBankCardRepository userBankCardRepository;

    public CardService(UserBankCardRepository userBankCardRepository) {
        this.userBankCardRepository = userBankCardRepository;
    }

    /**
     * Retrieves a user bank card by its card ID.
     * @param cardId the ID of the card to retrieve
     * @return the user bank card with the specified ID
     * @throws CardNotFoundException if no card is found with the specified ID
     */
    @Override
    public UserBankCard getCardById(String cardId){
        return userBankCardRepository.findUserBankCardByCardId(cardId)
                .orElseThrow(CardNotFoundException::new);
    }

    /**
     * Processes a payment using the specified card ID and amount.
     * @param cardId the ID of the card to use for payment
     * @param amount the amount to deduct from the card
     * @throws NotEnoughAmountOnCardException if the card does not have enough funds for the payment
     */
    @Override
    public void processPayment(String cardId, double amount){
        UserBankCard card = userBankCardRepository.findUserBankCardByCardId(cardId)
                .orElseThrow(NotEnoughAmountOnCardException::new);

        if(!(card.getAmount() >= Math.abs(amount))) throw new NotEnoughAmountOnCardException();

        card.setAmount(card.getAmount() - Math.abs(amount) );
        userBankCardRepository.save(card);
    }
}
