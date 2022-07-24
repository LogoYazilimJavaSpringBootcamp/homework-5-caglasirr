package com.example.movieapp.service;

import com.example.movieapp.model.SubscriptionType;
import com.example.movieapp.repository.SubscriptionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionTypeService {
    @Autowired
    private SubscriptionTypeRepository subscriptionTypeRepository;

    public SubscriptionType findById(int id){
        return subscriptionTypeRepository.findById(id).get();
    }
    List<SubscriptionType> subscriptionTypeList = new ArrayList<>();

    public List<SubscriptionType> findAll(){
        return subscriptionTypeRepository.findAll();
    }

    public void addSubscriptionTypes() {
        SubscriptionType s1 = new SubscriptionType("FREE", BigDecimal.valueOf(0));
        SubscriptionType s2 = new SubscriptionType("ONE_MONTH", BigDecimal.valueOf(100));
        SubscriptionType s3 = new SubscriptionType("THREE_MONTH", BigDecimal.valueOf(200));
        SubscriptionType s4 = new SubscriptionType("SIX_MONTH", BigDecimal.valueOf(300));
        SubscriptionType s5 = new SubscriptionType("TWELVE_MONTH", BigDecimal.valueOf(400));
        subscriptionTypeList.add(s1);
        subscriptionTypeList.add(s2);
        subscriptionTypeList.add(s3);
        subscriptionTypeList.add(s4);
        subscriptionTypeList.add(s5);
        subscriptionTypeRepository.saveAll(subscriptionTypeList);
    }
}
