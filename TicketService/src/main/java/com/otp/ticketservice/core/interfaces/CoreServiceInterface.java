package com.otp.ticketservice.core.interfaces;

public interface CoreServiceInterface {
    boolean validateUserToken( String token );
    boolean matchCardToUser( Long cardId, String token );
    boolean checkIfAmountIsAvailable(Long cardId, double amount);
}
