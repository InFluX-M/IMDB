package com.example.imdb.service;

import com.example.imdb.exception.EntityNotFoundException;
import com.example.imdb.model.Movie;
import com.example.imdb.model.requests.MovieRequest;
import com.example.imdb.model.responses.MovieResponse;
import com.example.imdb.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MovieService {

    private MovieRepository movieRepository;

    public MovieResponse addMovie(MovieRequest request) {
        // todo titleId tekrari
        Movie movie = Movie.builder()
                .titleId(request.getTitleId())
                .type(request.getType())
                .title(request.getTitle())
                .startYear(request.getStartYear())
                .endYear(request.getEndYear())
                .runtimeMinutes(request.getRuntimeMinutes())
                .genres(request.getGenres())
                .isAdult(request.getIsAdult())
                .build();
        return movieRepository.save(movie).response();
    }

    public void updateMovie(String titleId, MovieRequest request) {
        Movie movie = checkMovieId(titleId);
//        if(request.)
    }

    public void deleteMovie(String titleId) {
        movieRepository.deleteById(titleId);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(String titleId) {
        return checkMovieId(titleId);
    }

    public Movie checkMovieId(String titleId) {
        Optional<Movie> loaded = movieRepository.findById(titleId);
        if (loaded.isEmpty())
            throw new EntityNotFoundException(Movie.class.getName(), titleId);
        return loaded.get();
    }
}
