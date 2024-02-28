package com.otp.ticketservice.core.repository;

import com.otp.ticketservice.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
