package com.otp.ticketservice.core.repository;

import com.otp.ticketservice.core.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTokenRepository extends JpaRepository<UserToken,Long> {
    Optional<UserToken> findUserTokenByToken(String token);
}
