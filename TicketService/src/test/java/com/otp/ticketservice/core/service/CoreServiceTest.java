package com.otp.ticketservice.core.service;

import com.otp.ticketservice.core.entity.User;
import com.otp.ticketservice.core.entity.UserBankCard;
import com.otp.ticketservice.core.exception.CardNotMatchUserException;
import com.otp.ticketservice.core.exception.TokenNotFoundException;
import com.otp.ticketservice.core.interfaces.CardServiceInterface;
import com.otp.ticketservice.core.interfaces.TokenServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CoreServiceTest {
    @Mock
    private CardServiceInterface cardService;
    @Mock
    private TokenServiceInterface tokenService;

    private CoreService coreService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        coreService = new CoreService(cardService, tokenService);
    }
    @Test
    public void validateUserToken_ValidToken() {
        String token = "validToken";

        coreService.validateUserToken(token);

        verify(tokenService, times(1)).validate(token);
    }

    @Test
    public void validateUserToken_InvalidToken() {
        String invalidToken = "invalidToken";

        when(tokenService.validate(invalidToken)).thenThrow(new TokenNotFoundException());

        assertThrows(TokenNotFoundException.class, () -> coreService.validateUserToken(invalidToken));
        verify(tokenService, times(1)).validate(invalidToken);
    }

    @Test
    public void matchCardToUser_TokenOwnerMatchingCardOwner() {
        String token = "validToken";
        String cardId = "cardId";
        User tokenOwner = new User(1L, "User", "user@example.com");
        UserBankCard card = new UserBankCard("cardId",tokenOwner, 1L,123,"name",1000,"HUF");

        when(tokenService.getUserFromToken(token)).thenReturn(tokenOwner);
        when(cardService.getCardById(cardId)).thenReturn(card);


        assertDoesNotThrow(() -> coreService.matchCardToUser(cardId, token));
        verify(tokenService, times(1)).getUserFromToken(token);
        verify(cardService, times(1)).getCardById(cardId);
    }

    @Test
    public void matchCardToUser_TokenOwnerNotMatchingCardOwner() {
        String token = "validToken";
        String cardId = "cardId";
        User tokenOwner = new User(1L, "TokenOwner", "tokenowner@example.com");
        User cardOwner = new User(2L, "CardOwner", "cardowner@example.com");
        UserBankCard card = new UserBankCard(cardId, cardOwner, 1L, 123, "name", 1000, "HUF");

        when(tokenService.getUserFromToken(token)).thenReturn(tokenOwner);
        when(cardService.getCardById(cardId)).thenReturn(card);


        assertThrows(CardNotMatchUserException.class, () -> coreService.matchCardToUser(cardId, token));
        verify(tokenService, times(1)).getUserFromToken(token);
        verify(cardService, times(1)).getCardById(cardId);
    }

    @Test
    public void payWithCard_ShouldCallProcessPaymentWithCorrectParameters() {
        String cardId = "cardId";
        double amount = 50.0;

        coreService.payWithCard(cardId, amount);

        verify(cardService, times(1)).processPayment(cardId, amount);
    }


}