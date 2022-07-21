package com.example.movieapp.repository;

import com.example.movieapp.enums.SubscriptionType;
import com.example.movieapp.model.SubscriptionPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionPriceRepository extends JpaRepository<SubscriptionPrice, Integer> {
    SubscriptionPrice findBySubscriptionType(SubscriptionType subscriptionType);
}
