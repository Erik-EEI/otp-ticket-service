package com.otp.ticketservice.core.service.card;

import com.otp.ticketservice.core.entity.UserBankCard;
import com.otp.ticketservice.core.exception.CardNotFoundException;
import com.otp.ticketservice.core.exception.NotEnoughAmountOnCardException;
import com.otp.ticketservice.core.repository.UserBankCardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CardServiceTest {
    @Mock
    private UserBankCardRepository userBankCardRepository;

    private CardService cardService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        this.cardService = new CardService(userBankCardRepository);
    }

    @Test
    public void getCardById_CardExists_ReturnsCard() {
        String cardId = "validCardId";
        UserBankCard card = new UserBankCard();
        when(userBankCardRepository.findUserBankCardByCardId(cardId)).thenReturn(Optional.of(card));

        UserBankCard result = cardService.getCardById(cardId);

        assertNotNull(result);
        assertEquals(card,result);
        verify(userBankCardRepository, times(1)).findUserBankCardByCardId(cardId);
    }

    @Test
    public void getCardById_CardNotExists_ThrowsCardNotFoundException() {
        String cardId = "invalidCardId";
        when(userBankCardRepository.findUserBankCardByCardId(cardId)).thenReturn(Optional.empty());

        assertThrows(CardNotFoundException.class, () -> cardService.getCardById(cardId));

        verify(userBankCardRepository, times(1)).findUserBankCardByCardId(cardId);
    }

    @Test
    public void processPayment_AmountAvailable_ProcessesPayment() {
        String cardId = "validCardId";
        double amount = 100.0;
        UserBankCard card = new UserBankCard(cardId, null, 0L, 0, "", 200.0, "");
        when(userBankCardRepository.findUserBankCardByCardId(cardId)).thenReturn(Optional.of(card));

        assertDoesNotThrow(() -> cardService.processPayment(cardId, amount));

        verify(userBankCardRepository, times(1)).save(card);
    }

    @Test
    public void processPayment_AmountNotAvailable_ThrowsNotEnoughAmountOnCardException() {
        // Given
        String cardId = "validCardId";
        double amount = 300.0;
        UserBankCard card = new UserBankCard(cardId, null, 0L, 0, "", 200.0, "");
        when(userBankCardRepository.findUserBankCardByCardId(cardId)).thenReturn(Optional.of(card));

        // When/Then
        assertThrows(NotEnoughAmountOnCardException.class, () -> cardService.processPayment(cardId, amount));

        verify(userBankCardRepository, never()).save(any());
    }
}