package com.example.movieapp.service;

import com.example.movieapp.dto.EmailDto;
import com.example.movieapp.dto.GetRecommendedMovieDto;
import com.example.movieapp.dto.RecommendedMovieRequest;
import com.example.movieapp.enums.SubscriptionType;
import com.example.movieapp.model.RecommendedMovie;
import com.example.movieapp.model.User;
import com.example.movieapp.repository.RecommendedMovieRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendedMovieService {

    @Autowired
    private RecommendedMovieRepository recommendedMovieRepository;

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserService userService;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${message.movie.not_success}")
    private String MOVIE_NOT_SUCCESS;

    @Value("${message.movie.success}")
    private String MOVIE_SUCCESS;

    @Value("${message.usernotfound}")
    private String USERNOTFOUND_MESSAGE;

    //Kullanıcının sisteme film eklemesini sağlar.
    public String addRecommendedMovie(int userId, RecommendedMovieRequest recommendedMovieRequest) {
        User user;
        RecommendedMovie recommendedMovie;
        EmailDto emailDto;
        if (userService.userExists(userId)) {
            user = userService.findById(userId);
            recommendedMovie = new RecommendedMovie();
            //Kullanıcının ücretsiz üye olup olmadığının kontrolünü yapar.
            if(!user.getSubscriptionType().equals(SubscriptionType.FREE) ){
                recommendedMovie.setMovie(movieService.getMovieById(recommendedMovieRequest.getMovieId()));
                recommendedMovie.setComment(recommendedMovieRequest.getComment());
                recommendedMovie.setScore(recommendedMovieRequest.getScore());
                recommendedMovie.setUser(user);
            }else{
                //Kullanıcı ücretsiz ise eklediği film sayısının 3 adeti geçip geçmediğinin kontrolünü yapar.
                if(user.getRecommendedMovies().size() >= 3){
                    return MOVIE_NOT_SUCCESS;
                }
                recommendedMovie.setMovie(movieService.getMovieById(recommendedMovieRequest.getMovieId()));
                recommendedMovie.setComment(null);
                recommendedMovie.setScore(recommendedMovieRequest.getScore());
                recommendedMovie.setUser(user);
            }

            //User'ın önerdiği filmi kaydeden method.
            recommendedMovieRepository.save(recommendedMovie);

            //User film ekledikten sonra bütün kullanıcılara mail atılır.
            sendEmailToAllUsers(new EmailDto( "YENİ FİLM", "Sisteme yeni bir film eklenmiştir!"));

            return MOVIE_SUCCESS;
        }
        return USERNOTFOUND_MESSAGE;
    }

    //Bütün kullanıcılara email gitmesini sağlayan method.
    public void sendEmailToAllUsers(EmailDto emailDto){
        for(String email: userService.getAllEmails()){
            emailDto.setToEmail(email);
            rabbitTemplate.convertAndSend("movie.email", "movie.email", emailDto);
        }
    }

    //Id'si verilen user'ın eklediği filmleri getirir.
    public List<GetRecommendedMovieDto> findRecommendedMoviesByUserId(int userId){
        User user = userService.findById(userId);
        return recommendedMovieRepository.findAllMoviesByUserId(user);
    }

    //Bütün filmleri getiren method.
    public List<GetRecommendedMovieDto> findAllMovies(){
        return recommendedMovieRepository.findAllMovies();
    }

}
