package com.example.movieapp.dto;

import lombok.Data;

@Data
public class RecommendedMovieRequest {
    private int movieId;
    private String comment;
    private int score;
}
