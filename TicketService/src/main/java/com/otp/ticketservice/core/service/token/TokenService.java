package com.otp.ticketservice.core.service.token;

import com.otp.ticketservice.core.entity.User;
import com.otp.ticketservice.core.entity.UserToken;
import com.otp.ticketservice.core.exception.TokenNotFoundException;
import com.otp.ticketservice.core.interfaces.TokenServiceInterface;
import com.otp.ticketservice.core.repository.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing user tokens.
 */
@Service
public class TokenService implements TokenServiceInterface {
    private final UserTokenRepository userTokenRepository;

    @Autowired
    public TokenService(UserTokenRepository userTokenRepository) {
        this.userTokenRepository = userTokenRepository;
    }

    /**
     * Validates a user token.
     * @param token the token to validate
     * @return true if the token is valid, throws exception otherwise
     * @throws TokenNotFoundException if no token is found with the specified token
     */
    @Override
    public boolean validate( String token ){
        UserToken userToken = userTokenRepository.findUserTokenByToken(token)
                .orElseThrow(TokenNotFoundException::new);

        return true;
    }
    /**
     * Retrieves the user associated with the specified token.
     * @param token the token for which to retrieve the user
     * @return the user associated with the specified token
     * @throws TokenNotFoundException if no token is found
     */
    @Override
    public User getUserFromToken( String token ){
        UserToken userToken = userTokenRepository.findUserTokenByToken(token)
                .orElseThrow(TokenNotFoundException::new);

        return userToken.getUser();
    }
}
