package com.otp.ticketservice.core.service.token;

import com.otp.ticketservice.core.entity.User;
import com.otp.ticketservice.core.entity.UserToken;
import com.otp.ticketservice.core.exception.TokenNotFoundException;
import com.otp.ticketservice.core.interfaces.TokenServiceInterface;
import com.otp.ticketservice.core.repository.UserTokenRepository;
import org.springframework.stereotype.Service;

@Service
public class TokenService implements TokenServiceInterface {
    private final UserTokenRepository userTokenRepository;

    public TokenService(UserTokenRepository userTokenRepository) {
        this.userTokenRepository = userTokenRepository;
    }

    @Override
    public boolean validate( String token ){
        UserToken userToken = userTokenRepository.findUserTokenByToken(token)
                .orElseThrow(TokenNotFoundException::new);

        return true;
    }
    @Override
    public User getUserFromToken( String token ){
        UserToken userToken = userTokenRepository.findUserTokenByToken(token)
                .orElseThrow(TokenNotFoundException::new);

        return userToken.getUser();
    }
}
