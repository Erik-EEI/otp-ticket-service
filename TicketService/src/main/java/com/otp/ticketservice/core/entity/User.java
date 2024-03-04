package com.otp.ticketservice.core.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Represents a user entity in the system.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
}
