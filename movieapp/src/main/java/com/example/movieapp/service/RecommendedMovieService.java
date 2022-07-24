package com.example.movieapp.service;

import com.example.movieapp.dto.EmailDto;
import com.example.movieapp.dto.GetRecommendedMovieDto;
import com.example.movieapp.dto.RecommendedMovieRequest;
import com.example.movieapp.model.Movie;
import com.example.movieapp.model.RecommendedMovie;
import com.example.movieapp.model.User;
import com.example.movieapp.repository.RecommendedMovieRepository;
import com.example.movieapp.repository.UserRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendedMovieService {

    private final int movie_add_limit = 3;

    @Autowired
    private RecommendedMovieRepository recommendedMovieRepository;

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${message.movie.not_success}")
    private String MOVIE_NOT_SUCCESS;

    @Value("${message.movie.success}")
    private String MOVIE_SUCCESS;

    @Value("${message.usernotfound}")
    private String USERNOTFOUND_MESSAGE;

    @Value("${message.movie.already_added_movie}")
    private String MOVIE_ALREADY_EXIST;

    //Kullanıcının sisteme film eklemesini sağlar.
    public String addRecommendedMovie(int userId, RecommendedMovieRequest recommendedMovieRequest) {
        User user;
        Movie movie;
        RecommendedMovie recommendedMovie;
        EmailDto emailDto;
        if (userRepository.findById(userId).isPresent()) {
            user = userService.findById(userId);
            movie = movieService.getMovieById(recommendedMovieRequest.getMovieId());

            //Kullanıcının önceden aynı filmi ekleyip eklemediğinin kontrolünü yapar.
            if(findByUserAndMovieExists(user, movie)) return MOVIE_ALREADY_EXIST;

            //Requestten alınan bilgiler RecomendedMovie modeline setlenir:
            recommendedMovie = new RecommendedMovie();
            recommendedMovie.setMovie(movieService.getMovieById(recommendedMovieRequest.getMovieId()));
            recommendedMovie.setScore(recommendedMovieRequest.getScore());
            recommendedMovie.setUser(user);

            //Kullanıcının ücretsiz üye olup olmadığının kontrolünü yapar. Ücretsiz üye ise yorumu "0" olarak setler.
            if(!(user.getSubscriptionType().getId()==1)){
                recommendedMovie.setComment(recommendedMovieRequest.getComment());
            }else{
                //Kullanıcı ücretsiz ise eklediği film sayısının 3 adeti geçip geçmediğinin kontrolünü yapar.
                if(user.getRecommendedMovies().size() >= movie_add_limit){
                    return MOVIE_NOT_SUCCESS;
                }
                recommendedMovie.setComment("0");
            }

            //User'ın önerdiği filmi kaydeden method.
            recommendedMovieRepository.save(recommendedMovie);

            //User film ekledikten sonra bütün kullanıcılara mail atılır.
            sendEmailToAllUsers(new EmailDto( "YENİ FİLM", "Sisteme yeni bir film eklenmiştir!"));

            return MOVIE_SUCCESS;
        }
        return USERNOTFOUND_MESSAGE;
    }

    //Id'si verilen user'ın eklediği filmleri getirir.
    public List<GetRecommendedMovieDto> findRecommendedMoviesByUserId(int userId){
        User user = userService.findById(userId);
        List<RecommendedMovie> movieList = recommendedMovieRepository.findAllByUser(user);
        return turnRecommendedMovietoDto(movieList);
    }

    //Bütün filmleri getiren method.
    public List<GetRecommendedMovieDto> findAllMovies(){
        List<RecommendedMovie> movieList = recommendedMovieRepository.findAll();
        return turnRecommendedMovietoDto(movieList);
    }

    public List<GetRecommendedMovieDto> turnRecommendedMovietoDto(List<RecommendedMovie> list){
        List<GetRecommendedMovieDto> movieList = new ArrayList<>();
        for(RecommendedMovie m : list){
            GetRecommendedMovieDto movie = new GetRecommendedMovieDto();
            movie.setMovie(m.getMovie());
            movie.setComment(m.getComment());
            movie.setScore(m.getScore());
            movieList.add(movie);
        }
        return movieList;
    }

    //Kullanıcının yorumununu ve puanını güncellemesini sağlayan method.
    public void updateCommentAndScore(int userId, RecommendedMovieRequest request){
        User foundUser;
        Movie foundMovie;
        RecommendedMovie foundRecommendedMovie;
        if (userRepository.findById(userId).isPresent()){
            foundUser=userService.findById(userId);
            foundMovie=movieService.getMovieById(request.getMovieId());
            if(findByUserAndMovieExists(foundUser,foundMovie) && !(foundUser.getSubscriptionType().getId()==1)) {
                foundRecommendedMovie = recommendedMovieRepository.findByUserAndMovie(foundUser, foundMovie).get();
                foundRecommendedMovie.setComment(request.getComment());
                foundRecommendedMovie.setScore(request.getScore());
                recommendedMovieRepository.save(foundRecommendedMovie);
            }
        }
    }

    //Sisemdeki bütün kullanıcıların eklediği bütün filmleri getiren method.
    public boolean findByUserAndMovieExists(User user , Movie movie){
        return recommendedMovieRepository.findByUserAndMovie(user,movie).isPresent();
    }

    //Bütün kullanıcılara email gitmesini sağlayan method.
    public void sendEmailToAllUsers(EmailDto emailDto){
        for(String email: userService.getAllEmails()){
            emailDto.setToEmail(email);
            rabbitTemplate.convertAndSend("movie.email", "movie.email", emailDto);
        }
    }
}
