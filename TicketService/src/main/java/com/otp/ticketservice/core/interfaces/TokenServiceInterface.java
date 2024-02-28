package com.otp.ticketservice.core.interfaces;

import com.otp.ticketservice.core.entity.User;

public interface TokenServiceInterface {
    boolean validate( String token );
    User getUserFromToken( String token );
}
