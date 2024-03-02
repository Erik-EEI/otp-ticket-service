package com.otp.ticketservice.core.service.token;

import com.otp.ticketservice.core.entity.User;
import com.otp.ticketservice.core.entity.UserToken;
import com.otp.ticketservice.core.exception.TokenNotFoundException;
import com.otp.ticketservice.core.repository.UserTokenRepository;
import com.otp.ticketservice.core.service.CoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TokenServiceTest {

    @Mock
    private UserTokenRepository userTokenRepository;

    private TokenService tokenService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.tokenService = new TokenService(userTokenRepository);
    }
    @Test
    public void validate_ValidToken_ReturnsTrue() {
        String token = "validToken";
        UserToken userToken = new UserToken();
        userToken.setToken(token);
        when(userTokenRepository.findUserTokenByToken(token)).thenReturn(Optional.of(userToken));

        assertDoesNotThrow(() -> tokenService.validate(token));

        verify(userTokenRepository, times(1)).findUserTokenByToken(token);
    }

    @Test
    public void validate_InvalidToken_ThrowsTokenNotFoundException() {
        String token = "invalidToken";
        when(userTokenRepository.findUserTokenByToken(token)).thenReturn(Optional.empty());

        assertThrows(TokenNotFoundException.class, () -> tokenService.validate(token));

        verify(userTokenRepository, times(1)).findUserTokenByToken(token);
    }

    @Test
    public void getUserFromToken_ValidToken_ReturnsUser() {
        String token = "validToken";
        UserToken userToken = new UserToken();
        User testUser = new User();
        userToken.setToken(token);;
        userToken.setUser(testUser);
        when(userTokenRepository.findUserTokenByToken(token)).thenReturn(Optional.of(userToken));

        User user = tokenService.getUserFromToken(token);

        assertNotNull(user);
        assertEquals(user, testUser);
        verify(userTokenRepository, times(1)).findUserTokenByToken(token);
    }

    @Test
    public void getUserFromToken_InvalidToken_ThrowsTokenNotFoundException() {
        String token = "invalidToken";
        when(userTokenRepository.findUserTokenByToken(token)).thenReturn(Optional.empty());

        assertThrows(TokenNotFoundException.class, () -> tokenService.getUserFromToken(token));

        verify(userTokenRepository, times(1)).findUserTokenByToken(token);
    }
}
