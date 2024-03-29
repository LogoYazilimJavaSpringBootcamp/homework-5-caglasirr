package com.example.movieapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecommendedMovieRequest {
    private int movieId;
    private String comment;
    private int score;
}
