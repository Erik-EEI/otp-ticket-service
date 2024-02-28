package com.otp.ticketservice.core.repository;

import com.otp.ticketservice.core.entity.UserBankCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBankCardRepository extends JpaRepository<UserBankCard,Long> {
}
