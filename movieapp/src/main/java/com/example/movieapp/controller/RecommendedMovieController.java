package com.example.movieapp.controller;

import com.example.movieapp.dto.GetRecommendedMovieDto;
import com.example.movieapp.dto.RecommendedMovieRequest;
import com.example.movieapp.service.RecommendedMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/movieapp")
public class RecommendedMovieController {

    @Autowired
    private RecommendedMovieService recommendedMovieService;

    @PostMapping(value = "/user/{userId}/movie")
    public String addRecommendedMovie(@PathVariable int userId, @RequestBody RecommendedMovieRequest recommendedMovieRequest) {
        return recommendedMovieService.addRecommendedMovie(userId, recommendedMovieRequest);
    }

    @GetMapping(value = "/user/{userId}/movie")
    public List<GetRecommendedMovieDto> findRecommendedMoviesByUserId(@PathVariable int userId){
        return recommendedMovieService.findRecommendedMoviesByUserId(userId);
    }

    @GetMapping(value = "/movies")
    public List<GetRecommendedMovieDto> findAllMovies(){
        return recommendedMovieService.findAllMovies();
    }
}
