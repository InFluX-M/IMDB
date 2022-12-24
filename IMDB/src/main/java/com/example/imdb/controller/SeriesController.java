package com.example.imdb.controller;

import com.example.imdb.model.responses.EpisodeResponse;
import com.example.imdb.model.responses.SeriesResponse;
import com.example.imdb.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/series")
public class SeriesController {

    private MovieService movieService;

    @GetMapping("/{titleId}")
    public ResponseEntity<EpisodeResponse> getSeries(@PathVariable String titleId) {
        return new ResponseEntity<>(movieService.getSeriesById(titleId), HttpStatus.OK);
    }

}
