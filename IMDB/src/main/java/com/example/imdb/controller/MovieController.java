package com.example.imdb.controller;

import com.example.imdb.model.requests.MovieRequest;
import com.example.imdb.model.responses.MovieResponse;
import com.example.imdb.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MovieController {

    private MovieService movieService;

    @PostMapping("/movies/new")
    public MovieResponse addMovie(@RequestBody MovieRequest request) {
        return movieService.addMovie(request);
    }
}
