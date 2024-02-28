package com.otp.ticketservice.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_bank_cards")
public class UserBankCard {
    @Id
    private String cardId;

    @OneToOne()
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    private Long cardNumber;
    private int cvc;
    private String name;
    private double amount;
    private String currency;
}
