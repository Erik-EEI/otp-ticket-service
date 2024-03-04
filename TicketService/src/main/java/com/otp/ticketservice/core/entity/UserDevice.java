package com.otp.ticketservice.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a device entity associated with a user.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_devices")
public class UserDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    private String deviceHash;
}
