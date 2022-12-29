package com.example.imdb.controller;

import com.example.imdb.model.responses.*;
import com.example.imdb.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/file")
public class FileController {

    private FileService fileService;

    @PostMapping("/readMovies")
    public ResponseEntity<List<MovieResponse>> readMovies() throws IOException {
        return new ResponseEntity<>(fileService.readMovies(), HttpStatus.CREATED);
    }

    @PostMapping("/readPersons")
    public ResponseEntity<List<PersonResponse>> readPersons() throws IOException {
        return new ResponseEntity<>(fileService.readPersons(), HttpStatus.CREATED);
    }

    @PostMapping("/readRatings")
    public ResponseEntity<List<RatingResponse>> readRatings() throws IOException {

        return new ResponseEntity<>(fileService.readRatings(), HttpStatus.CREATED);
    }

    @PostMapping("/readEpisodes")
    public ResponseEntity<List<EpisodeResponse>> readEpisodes() throws IOException {
        return new ResponseEntity<>(fileService.readEpisodes(), HttpStatus.CREATED);
    }

    @PostMapping("/readCrews")
    public ResponseEntity<List<DirectorResponse>> readCrews() throws IOException {
        return new ResponseEntity<>(fileService.readCrews(), HttpStatus.CREATED);
    }

}
