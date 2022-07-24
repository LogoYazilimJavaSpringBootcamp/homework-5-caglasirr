package com.example.movieapp.service;

import com.example.movieapp.dto.*;
import com.example.movieapp.model.Index;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
//deneme
@Service
public class IndexService {
    List<Index> indexList = new ArrayList<>();

    public List<Index> getAllIndexes(){

        Index index1 = new Index("localhost:8082/users/movies","Get","Databasede bulunan ve kullanıcıların ekleyebileceği filmleri (Movie tablosu) getiren endpoint.");
        Index index2 = new Index("localhost:8082/users/subscription-types","Get","Kullanıcının seçebileceği üyelik tiplerini (SubscriptionType tablosu) getiren endpoint.");

        UserRegisterRequest userRegisterRequest = new UserRegisterRequest("Çağla", "Sır","1234567","caglasir4@gmail.com", 1);
        Index index3 = new Index("localhost:8082/users/register","Post", "Kullanıcının sisteme kayıt olmasını sağlayan endpoint.", userRegisterRequest);

        UserLoginRequest userLoginRequest = new UserLoginRequest("caglasir4@gmail.com", "1234567");
        Index index4 = new Index("localhost:8082/users/login","Post", "Kullanıcının sisteme login olmasını sağlayan endpoint.", userLoginRequest);

        UserChangeInfoRequest userChangeInfoRequest = new UserChangeInfoRequest("newname", "newpassword");
        Index index5 = new Index("localhost:8082/users/{userId}", "Put", "Path variable olarak verilen userid'ye sahip kullanıcının ismini ve şifresini değiştirmesini sağlayan endpoint.",userChangeInfoRequest);

        RecommendedMovieRequest recommendedMovieRequest = new RecommendedMovieRequest(1,"It was a nice movie.",7);
        Index index6 = new Index("localhost:8082/users/{userId}/movie", "Post", "Path variable olarak verilen userid'ye sahip kullanıcının sisteme film eklemesini sağlayan endpoint.",recommendedMovieRequest);

        Index index7 = new Index("localhost:8082/users/{userId}/movie", "Get", "Path variable olarak verilen userid'ye sahip kullanıcının eklediği filmleri getiren endpoint.");
        Index index8 = new Index("localhost:8082/users/recommended-movies", "Get", "Sisemdeki bütün kullanıcıların eklediği bütün filmleri getiren endpoint.");
        Index index9 = new Index("localhost:8082/users/{userId}/movie", "Put", "Kullanıcının sisteme önceden eklediği filmin yorumunu ve puanını güncellemesini sağlar.",recommendedMovieRequest);

        indexList.add(index1);
        indexList.add(index2);
        indexList.add(index3);
        indexList.add(index4);
        indexList.add(index5);
        indexList.add(index6);
        indexList.add(index7);
        indexList.add(index8);
        indexList.add(index9);
        return indexList;

    }
}
