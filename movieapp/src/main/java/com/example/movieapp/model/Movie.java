package com.example.movieapp.model;

import com.example.movieapp.enums.Category;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String year;
    private String director;
    @Enumerated(EnumType.STRING)
    private Category category;

    public Movie(String name, String year, String director, Category category) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.category = category;
    }
}
