package com.otp.ticketservice.core.service;

import com.otp.ticketservice.core.entity.User;
import com.otp.ticketservice.core.exception.CardNotMatchUserException;
import com.otp.ticketservice.core.interfaces.CardServiceInterface;
import com.otp.ticketservice.core.interfaces.CoreServiceInterface;
import com.otp.ticketservice.core.interfaces.TokenServiceInterface;
import com.otp.ticketservice.core.service.card.CardService;
import com.otp.ticketservice.core.service.token.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for core operations related to user validation, card matching, and payment processing.
 */
@Service
public class CoreService implements CoreServiceInterface {
    private final CardServiceInterface cardService;
    private final TokenServiceInterface tokenService;
    private final Logger LOGGER = LoggerFactory.getLogger("[CORE SERVICE]");

    @Autowired
    public CoreService(CardServiceInterface cardService, TokenServiceInterface tokenService) {
        this.cardService = cardService;
        this.tokenService = tokenService;
    }

    /**
     * Validates a user token.
     * @param token the token to validate
     */
    @Override
    public void validateUserToken( String token ){
        tokenService.validate(token);
        LOGGER.info(String.format("✔ - VALID -- Valid token -> %s", token));
    }

    /**
     * Matches a card to a user using tokenService and cardService with the provided card ID and token.
     * @param cardId the ID of the card to match
     * @param token the token associated with the user
     * @throws CardNotMatchUserException if the card does not match the user associated with the token
     */
    @Override
    public void matchCardToUser(String cardId, String token ){
        User tokenOwner = tokenService.getUserFromToken( token );
        User cardOwner = cardService.getCardById( cardId ).getUser();

        if(!tokenOwner.equals(cardOwner)) throw new CardNotMatchUserException();
        LOGGER.info(String.format("✔ - VALID -- Card %s matched user %s",cardId,cardOwner.getName()));
    }

    /**
     * Processes a payment using cardService with the specified card ID and amount.
     * @param cardId the ID of the card to use for payment
     * @param amount the amount to deduct from the card
     */
    @Override
    public void payWithCard(String cardId, double amount){
         cardService.processPayment(cardId,amount);
         LOGGER.info(String.format("✔ - VALID -- Requested amount is available on card %S",cardId));
    }
}
