package com.example.movieapp.controller;

import com.example.movieapp.dto.UserChangeNameRequest;
import com.example.movieapp.dto.UserChangePasswordRequest;
import com.example.movieapp.dto.UserLoginRequest;
import com.example.movieapp.dto.UserRegisterRequest;
import com.example.movieapp.model.User;
import com.example.movieapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/movieapp/user")
public class UserController {

    @Autowired
    private UserService userService;

    //Kullanıcının sisteme kayıt olmasını sağlayan method:
    @PostMapping("/register")
    public String register(@RequestBody UserRegisterRequest userRegisterRequest) {
        return userService.register(userRegisterRequest);
    }

    //Kullanıcının sisteme login olmasını sağlayan method:
    @PostMapping("/login")
    public String login(@RequestBody UserLoginRequest userLoginRequest){
       return userService.login(userLoginRequest);
    }

    //Kullanıcının şifresini değiştirmesini sağlayan method:
    @PutMapping("{userId}/change/password")
    public void changePassword(@PathVariable int userId, @RequestBody UserChangePasswordRequest userChangePasswordRequest){
         userService.changePassword(userId, userChangePasswordRequest);
    }

    //Kullanıcının ismini değiştirmesini sağlayan method:
    @PutMapping("{userId}/change/name")
    public void changeName(@PathVariable int userId, @RequestBody UserChangeNameRequest userChangeNameRequest){
        userService.changeName(userId, userChangeNameRequest);
    }

}
