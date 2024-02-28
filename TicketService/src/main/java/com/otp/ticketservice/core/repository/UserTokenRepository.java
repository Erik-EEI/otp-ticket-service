package com.otp.ticketservice.core.repository;

import com.otp.ticketservice.core.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTokenRepository extends JpaRepository<UserToken,Long> {
}
