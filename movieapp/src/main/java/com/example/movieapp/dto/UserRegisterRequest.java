package com.example.movieapp.dto;

import com.example.movieapp.enums.SubscriptionType;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
public class UserRegisterRequest {
    private String name;
    private String surname;
    private String password;
    private String email;
    @Enumerated(EnumType.ORDINAL)
    private SubscriptionType subscriptionType;
}
