package com.example.movieapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "subscription_type")
public class SubscriptionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private BigDecimal amount;

    public SubscriptionType(String name, BigDecimal amount) {
        this.name = name;
        this.amount = amount;
    }
}
