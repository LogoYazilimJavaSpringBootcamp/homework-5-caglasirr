package com.example.movieapp.controller;

import com.example.movieapp.model.Index;
import com.example.movieapp.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexController {

    @Autowired
    private IndexService indexService;

    @GetMapping
    public List<Index> getAllIndexes(){
        return indexService.getAllIndexes();
    }
}
