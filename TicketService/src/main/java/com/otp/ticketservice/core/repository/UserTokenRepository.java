package com.otp.ticketservice.core.repository;

import com.otp.ticketservice.core.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken,Long> {
    Optional<UserToken> findUserTokenByToken(String token);
}
