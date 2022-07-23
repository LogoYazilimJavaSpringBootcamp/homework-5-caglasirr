package com.example.movieapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserChangePasswordRequest {
    private String password;
}
