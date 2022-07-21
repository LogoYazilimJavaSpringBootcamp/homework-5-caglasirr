package com.example.movieapp.model;

import com.example.movieapp.enums.SubscriptionType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String password;
    private String email;
    @Enumerated(EnumType.ORDINAL)
    private SubscriptionType subscriptionType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private List<RecommendedMovie> recommendedMovies;
}
