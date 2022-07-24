package com.example.movieapp.repository;

import com.example.movieapp.dto.GetRecommendedMovieDto;
import com.example.movieapp.model.Movie;
import com.example.movieapp.model.RecommendedMovie;
import com.example.movieapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecommendedMovieRepository extends JpaRepository<RecommendedMovie,Integer> {

    List<RecommendedMovie> findAllByUser(User user);

    Optional<RecommendedMovie> findByUserAndMovie(User user, Movie movie);

}
