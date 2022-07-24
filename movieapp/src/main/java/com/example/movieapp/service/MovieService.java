package com.example.movieapp.service;

import com.example.movieapp.enums.Category;
import com.example.movieapp.model.Movie;
import com.example.movieapp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public Movie getMovieById(int movieId){
        return movieRepository.findById(movieId).orElse(null);
    }

    //Uygulama başlatıldığında db'ye filmleri ekler.
    public void addMovies(){
        List<Movie> movieList = new ArrayList<>();
        Movie movie1 = new Movie("Movie1","1995", "Director1", Category.Romance);
        Movie movie2 = new Movie("Movie2","1992", "Director2", Category.Romance);
        Movie movie3 = new Movie("Movie3","1993", "Director3", Category.Romance);
        Movie movie4 = new Movie("Movie4","1994", "Director4", Category.Romance);
        Movie movie5 = new Movie("Movie5","2018", "Director5", Category.Romance);
        Movie movie6 = new Movie("Movie6","2002", "Director6", Category.Romance);
        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);
        movieList.add(movie4);
        movieList.add(movie5);
        movieList.add(movie6);
        movieRepository.saveAll(movieList);
    }
}
