package com.example.movieapp.model;

import com.example.movieapp.enums.SubscriptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private SubscriptionType subscriptionType;
    private BigDecimal amount;

    public SubscriptionPrice(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "SubscriptionPrice{" +
                "id=" + id +
                ", subscriptionType=" + subscriptionType +
                ", amount=" + amount +
                '}';
    }

    public SubscriptionPrice(SubscriptionType subscriptionType, BigDecimal amount) {
        this.subscriptionType = subscriptionType;
        this.amount = amount;
    }
}
