package com.otp.ticketservice.core.service;

import com.otp.ticketservice.core.entity.User;
import com.otp.ticketservice.core.service.card.CardService;
import com.otp.ticketservice.core.service.token.TokenService;
import com.otp.ticketservice.core.service.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class CoreService {
    private final CardService cardService;
    private final TokenService tokenService;
    // TODO Change dependency to Interfaces

    public CoreService(CardService cardService, TokenService tokenService) {
        this.cardService = cardService;
        this.tokenService = tokenService;
    }

    public boolean validateUserToken( String token ){
        return tokenService.validate( token );
    }

    public boolean matchCardToUser( Long cardId, String token ){
        User tokenOwner = tokenService.getUserFromToken( token );
        User cardOwner = cardService.getCardById( cardId ).getUser();

        return tokenOwner.equals(cardOwner);
    }

    public boolean checkIfAmountIsAvailable(Long cardId, double amount){
        return cardService.checkIfAmountIsAvailable(cardId,amount);
    }
}
