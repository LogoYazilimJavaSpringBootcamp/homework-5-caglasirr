package com.example.movieapp.service;

import com.example.movieapp.dto.*;
import com.example.movieapp.enums.SubscriptionType;
import com.example.movieapp.model.Index;
import com.example.movieapp.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IndexService {
    List<Index> indexList = new ArrayList<>();

    public List<Index> getAllIndexes(){
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest("Çağla", "Sır","1234567","caglasir4@gmail.com", SubscriptionType.ONE_MONTH);
        Index index1 = new Index("localhost:8082/movieapp/user/register","Post", "Kullanıcının sisteme kayıt olmasını sağlayan endpoint.", userRegisterRequest);
        indexList.add(index1);

        UserLoginRequest userLoginRequest = new UserLoginRequest("caglasir4@gmail.com", "1234567");
        Index index2 = new Index("localhost:8082/movieapp/user/login","Post", "Kullanıcının sisteme login olmasını sağlayan endpoint.", userLoginRequest);
        indexList.add(index2);

        UserChangePasswordRequest userChangePasswordRequest = new UserChangePasswordRequest("newpassword");
        Index index3 = new Index("localhost:8082/movieapp/user/{userId}/change/password", "Post","Kullanıcının şifresini değiştirmesini sağlayan endpoint.",userChangePasswordRequest);
        indexList.add(index3);

        UserChangeNameRequest userChangeNameRequest = new UserChangeNameRequest("newname");
        Index index4 = new Index("localhost:8082/movieapp/user/{userId}/change/name", "Post", "Kullanıcının ismini değiştirmesini sağlayan endpoint.",userChangeNameRequest);
        indexList.add(index4);

        RecommendedMovieRequest recommendedMovieRequest = new RecommendedMovieRequest(1,"It was a nice movie.",7);
        Index index5 = new Index("localhost:8082/movieapp/user/{userId}/movie", "Post", "Kullanıcının sisteme film eklemesini sağlayan endpoint.",recommendedMovieRequest);

        Index index6 = new Index("localhost:8082/movieapp/user/{userId}/movie", "Get", "Verilen userId'ye sahip kullanıcının eklediği filmleri getiren endpoint.");
        Index index7 = new Index("localhost:8082/movieapp/movies", "Get", "Sisemdeki bütün kullanıcıların eklediği bütün filmleri getiren endpoint.");

        indexList.add(index5);
        indexList.add(index6);
        indexList.add(index7);
        return indexList;

    }
}
