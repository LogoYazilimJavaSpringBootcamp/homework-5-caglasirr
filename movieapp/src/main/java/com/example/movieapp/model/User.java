package com.example.movieapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
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
    //@Enumerated(EnumType.ORDINAL)
    @OneToOne
    private SubscriptionType subscriptionType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private List<RecommendedMovie> recommendedMovies;

    public User(String name, String surname, String password, String email, SubscriptionType subscriptionType) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.subscriptionType = subscriptionType;
    }
}
