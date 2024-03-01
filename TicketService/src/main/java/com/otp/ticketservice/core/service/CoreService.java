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
import org.springframework.stereotype.Service;

@Service
public class CoreService implements CoreServiceInterface {
    private final CardServiceInterface cardService;
    private final TokenServiceInterface tokenService;
    private final Logger LOGGER = LoggerFactory.getLogger("[CORE MODULE]");

    public CoreService(CardService cardService, TokenService tokenService) {
        this.cardService = cardService;
        this.tokenService = tokenService;
    }

    @Override
    public void validateUserToken(String token ){
        tokenService.validate(token);
        LOGGER.info(String.format("Valid token -> %s", token));
    }

    @Override
    public void matchCardToUser(String cardId, String token ){
        User tokenOwner = tokenService.getUserFromToken( token );
        User cardOwner = cardService.getCardById( cardId ).getUser();

        if(!tokenOwner.equals(cardOwner)) throw new CardNotMatchUserException();
        LOGGER.info(String.format("Card %s matched user %s",cardId,cardOwner.getName()));
    }

    @Override
    public void checkIfAmountIsAvailable(String cardId, double amount){
         cardService.checkIfAmountIsAvailable(cardId,amount);
         LOGGER.info(String.format("Requested amount is available on card %S",cardId));
    }
}
