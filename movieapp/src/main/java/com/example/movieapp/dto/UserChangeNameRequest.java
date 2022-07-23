package com.example.movieapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserChangeNameRequest {
    private String name;
}
