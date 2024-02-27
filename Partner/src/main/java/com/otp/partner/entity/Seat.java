package com.otp.partner.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String seatName;
    private int price;
    private String currency;
    private boolean reserved;

    @ManyToOne
    @JoinColumn(name = "event_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Event event;
}
