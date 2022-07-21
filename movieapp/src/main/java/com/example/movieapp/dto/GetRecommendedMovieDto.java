package com.example.movieapp.dto;

import com.example.movieapp.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetRecommendedMovieDto {
    private Movie movie;
    private String comment;
    private int score;
}
