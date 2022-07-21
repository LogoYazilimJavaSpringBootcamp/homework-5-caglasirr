package com.example.movieapp.controller;

import com.example.movieapp.dto.UserLoginRequest;
import com.example.movieapp.model.User;
import com.example.movieapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/movieapp/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginRequest request){
       return userService.login(request);
    }

    @PutMapping("{userId}/change/password")
    public void changePassword(@PathVariable int userId, @RequestBody User user){
         userService.changePassword(userId, user);
    }

    @PutMapping("{userId}/change/name")
    public void changeName(@PathVariable int userId, @RequestBody User user){
        userService.changeName(userId, user);
    }

}
