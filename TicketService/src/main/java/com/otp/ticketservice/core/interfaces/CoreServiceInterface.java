package com.otp.ticketservice.core.interfaces;

public interface CoreServiceInterface {
    void validateUserToken(String token );
    void matchCardToUser(String cardId, String token );
    void checkIfAmountIsAvailable(String cardId, double amount);
}
