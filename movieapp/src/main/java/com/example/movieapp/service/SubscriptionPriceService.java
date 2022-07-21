package com.example.movieapp.service;

import com.example.movieapp.enums.SubscriptionType;
import com.example.movieapp.model.SubscriptionPrice;
import com.example.movieapp.repository.SubscriptionPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionPriceService {

    @Autowired
    SubscriptionPriceRepository subscriptionPriceRepository;

    public void addSubscriptionPrices(){
        List<SubscriptionPrice> subscriptionPriceList = new ArrayList<>();
        SubscriptionPrice subscriptionPrice1 = new SubscriptionPrice(SubscriptionType.FREE, BigDecimal.valueOf(0));
        SubscriptionPrice subscriptionPrice2 = new SubscriptionPrice(SubscriptionType.ONE_MONTH, BigDecimal.valueOf(100));
        SubscriptionPrice subscriptionPrice3 = new SubscriptionPrice(SubscriptionType.THREE_MONTH, BigDecimal.valueOf(200));
        SubscriptionPrice subscriptionPrice4 = new SubscriptionPrice(SubscriptionType.SIX_MONTH, BigDecimal.valueOf(300));
        SubscriptionPrice subscriptionPrice5 = new SubscriptionPrice(SubscriptionType.TWELVE_MONTH, BigDecimal.valueOf(400));
        subscriptionPriceList.add(subscriptionPrice1);
        subscriptionPriceList.add(subscriptionPrice2);
        subscriptionPriceList.add(subscriptionPrice3);
        subscriptionPriceList.add(subscriptionPrice4);
        subscriptionPriceList.add(subscriptionPrice5);
        subscriptionPriceRepository.saveAll(subscriptionPriceList);
    }

}
