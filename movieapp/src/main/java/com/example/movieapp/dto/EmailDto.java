package com.example.movieapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {
    private String toEmail;
    private String subject;
    private String body;

    public EmailDto(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }
}
