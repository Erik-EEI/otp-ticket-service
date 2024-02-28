package com.otp.ticketservice.core.service;

import com.otp.ticketservice.core.entity.UserToken;
import com.otp.ticketservice.core.repository.UserTokenRepository;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    private final UserTokenRepository userTokenRepository;

    public TokenService(UserTokenRepository userTokenRepository) {
        this.userTokenRepository = userTokenRepository;
    }

    public boolean validate( String token ){
        UserToken userToken = userTokenRepository.findUserTokenByToken(token)
                .orElseThrow(RuntimeException::new); //TODO Replace with custom exception

        return true;
    }
}
