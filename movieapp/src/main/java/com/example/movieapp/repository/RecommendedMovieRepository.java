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

    @Query(value = "SELECT new com.example.movieapp.dto.GetRecommendedMovieDto(r.movie,r.comment,r.score)"+ "FROM RecommendedMovie r where r.user=?1")
    List<GetRecommendedMovieDto> findAllMoviesByUserId(User user);

    @Query(value = "SELECT new com.example.movieapp.dto.GetRecommendedMovieDto(r.movie,r.comment,r.score)"+ "FROM RecommendedMovie r")
    List<GetRecommendedMovieDto> findAllMovies();

    Optional<RecommendedMovie> findByUserAndMovie(User user, Movie movie);

}
