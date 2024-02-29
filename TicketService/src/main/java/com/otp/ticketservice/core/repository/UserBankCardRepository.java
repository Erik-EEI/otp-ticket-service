package com.otp.ticketservice.core.repository;

import com.otp.ticketservice.core.entity.UserBankCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserBankCardRepository extends JpaRepository<UserBankCard,Long> {
    Optional<UserBankCard> findUserBankCardByUserId(Long userId);
    Optional<UserBankCard> findUserBankCardByCardId(String cardId);
}
