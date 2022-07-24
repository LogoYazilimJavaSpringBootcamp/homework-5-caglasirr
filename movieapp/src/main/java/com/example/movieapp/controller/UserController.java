package com.example.movieapp.controller;

import com.example.movieapp.dto.UserChangeInfoRequest;
import com.example.movieapp.dto.UserLoginRequest;
import com.example.movieapp.dto.UserRegisterRequest;
import com.example.movieapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody UserRegisterRequest userRegisterRequest) {
        return userService.register(userRegisterRequest);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginRequest userLoginRequest){
       return userService.login(userLoginRequest);
    }

    //Kullanıcının ismini değiştirmesini sağlayan method:
    @PutMapping("{userId}")
    public void changeName(@PathVariable int userId, @RequestBody UserChangeInfoRequest userChangeInfoRequest){
        userService.changeUserInfo(userId, userChangeInfoRequest);
    }

}
