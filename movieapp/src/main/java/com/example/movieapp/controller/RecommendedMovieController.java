package com.example.movieapp.controller;

import com.example.movieapp.dto.GetRecommendedMovieDto;
import com.example.movieapp.dto.RecommendedMovieRequest;
import com.example.movieapp.model.Movie;
import com.example.movieapp.service.MovieService;
import com.example.movieapp.service.RecommendedMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/users")
public class RecommendedMovieController {

    @Autowired
    private RecommendedMovieService recommendedMovieService;

    @Autowired
    private MovieService movieService;

    //Kullanıcının sisteme yorumu ve puanı ile birlikte film eklemesini sağlayan endpoint.
    @PostMapping(value = "/{userId}/movie")
    public String addRecommendedMovie(@PathVariable int userId, @RequestBody RecommendedMovieRequest recommendedMovieRequest) {
        return recommendedMovieService.addRecommendedMovie(userId, recommendedMovieRequest);
    }

    //Kullanıcının yorumununu ve puanını güncellemesini sağlayan endpoint.
    @PutMapping("/{userId}/movie")
    public void updateCommentAndScore(@PathVariable int userId, @RequestBody RecommendedMovieRequest recommendedMovieRequest){
        recommendedMovieService.updateCommentAndScore(userId,recommendedMovieRequest);
    }

    //Verilen userId'ye sahip kullanıcının eklediği filmleri getiren endpoint.
    @GetMapping(value = "/{userId}/movie")
    public List<GetRecommendedMovieDto> findRecommendedMoviesByUserId(@PathVariable int userId){
        return recommendedMovieService.findRecommendedMoviesByUserId(userId);
    }

    //Sisemdeki bütün kullanıcıların eklediği bütün filmleri getiren endpoint.
    @GetMapping(value = "/recommended-movies")
    public List<GetRecommendedMovieDto> findAllReMovies(){
        return recommendedMovieService.findAllMovies();
    }

    //Database'de bulunan ve kullanıcının ekleyebileceği bütün filmleri getiren endpoint.
    @GetMapping(value = "/movies")
    public List<Movie> movies(){
        return movieService.getAllMovies();
    }

}
