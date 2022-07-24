package com.example.movieapp.dto;

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
    private int subscriptionTypeId;
}
