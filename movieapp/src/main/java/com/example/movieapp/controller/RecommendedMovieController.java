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

    //Kullanıcının sisteme yorumu ve puanı ile birlikte film eklemesini sağlayan endpoint.
    @PostMapping(value = "/user/{userId}/movie")
    public String addRecommendedMovie(@PathVariable int userId, @RequestBody RecommendedMovieRequest recommendedMovieRequest) {
        return recommendedMovieService.addRecommendedMovie(userId, recommendedMovieRequest);
    }

    //Verilen userId'ye sahip kullanıcının eklediği filmleri getiren endpoint.
    @GetMapping(value = "/user/{userId}/movie")
    public List<GetRecommendedMovieDto> findRecommendedMoviesByUserId(@PathVariable int userId){
        return recommendedMovieService.findRecommendedMoviesByUserId(userId);
    }

    //Sisemdeki bütün kullanıcıların eklediği bütün filmleri getiren endpoint.
    @GetMapping(value = "/movies")
    public List<GetRecommendedMovieDto> findAllMovies(){
        return recommendedMovieService.findAllMovies();
    }

    //Kullanıcının yorumununu ve puanını güncellemesini sağlayan endpoint.
    @PutMapping("/user/{userId}/movie")
    public void updateCommentAndScore(@PathVariable int userId, @RequestBody RecommendedMovieRequest recommendedMovieRequest){
        recommendedMovieService.updateCommentAndScore(userId,recommendedMovieRequest);
    }
}
