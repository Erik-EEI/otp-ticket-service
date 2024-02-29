package com.otp.ticketservice.core.service;

import com.otp.ticketservice.core.entity.User;
import com.otp.ticketservice.core.exception.CardNotMatchUserException;
import com.otp.ticketservice.core.interfaces.CardServiceInterface;
import com.otp.ticketservice.core.interfaces.CoreServiceInterface;
import com.otp.ticketservice.core.interfaces.TokenServiceInterface;
import com.otp.ticketservice.core.service.card.CardService;
import com.otp.ticketservice.core.service.token.TokenService;
import org.springframework.stereotype.Service;

@Service
public class CoreService implements CoreServiceInterface {
    private final CardServiceInterface cardService;
    private final TokenServiceInterface tokenService;

    public CoreService(CardService cardService, TokenService tokenService) {
        this.cardService = cardService;
        this.tokenService = tokenService;
    }

    @Override
    public void validateUserToken(String token ){
        tokenService.validate(token);
    }

    @Override
    public void matchCardToUser(String cardId, String token ){
        User tokenOwner = tokenService.getUserFromToken( token );
        User cardOwner = cardService.getCardById( cardId ).getUser();

        if(!tokenOwner.equals(cardOwner)) throw new CardNotMatchUserException();
    }

    @Override
    public void checkIfAmountIsAvailable(String cardId, double amount){
         cardService.checkIfAmountIsAvailable(cardId,amount);
    }
}
