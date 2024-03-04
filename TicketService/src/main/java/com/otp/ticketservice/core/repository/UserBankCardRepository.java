package com.otp.ticketservice.core.repository;

import com.otp.ticketservice.core.entity.UserBankCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserBankCardRepository extends JpaRepository<UserBankCard,Long> {
    Optional<UserBankCard> findUserBankCardByUserId(Long userId);
    Optional<UserBankCard> findUserBankCardByCardId(String cardId);
}
