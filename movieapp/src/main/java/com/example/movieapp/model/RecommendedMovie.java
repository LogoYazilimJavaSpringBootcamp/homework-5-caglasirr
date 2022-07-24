package com.example.movieapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "recommended_movie")
public class RecommendedMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private Movie movie;
    @ManyToOne
    private User user;
    private String comment;
    private int score;
}
