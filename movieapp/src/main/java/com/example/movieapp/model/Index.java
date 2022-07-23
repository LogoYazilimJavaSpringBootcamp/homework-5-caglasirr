package com.example.movieapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Index {
    private String link;
    private String httpMethodType;
    private String purpose;
    private Object data;

    public Index(String link, String httpMethodType, String purpose) {
        this.link = link;
        this.httpMethodType = httpMethodType;
        this.purpose = purpose;
    }
}
