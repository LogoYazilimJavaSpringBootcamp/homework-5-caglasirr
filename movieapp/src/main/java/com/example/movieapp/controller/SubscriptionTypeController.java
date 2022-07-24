package com.example.movieapp.controller;

import com.example.movieapp.model.SubscriptionType;
import com.example.movieapp.service.SubscriptionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/users")
public class SubscriptionTypeController {

    @Autowired
    private SubscriptionTypeService subscriptionTypeService;

    @GetMapping(value = "/subscription-types")
    public List<SubscriptionType> getSubscriptionTypes(){
        return subscriptionTypeService.findAll();
    }
}
