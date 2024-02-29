package com.otp.ticketservice.core.interfaces;

public interface CoreServiceInterface {
    boolean validateUserToken( String token );
    boolean matchCardToUser( String cardId, String token );
    boolean checkIfAmountIsAvailable(String cardId, double amount);
}
