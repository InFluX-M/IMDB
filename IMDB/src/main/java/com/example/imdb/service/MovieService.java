package com.example.imdb.service;

import com.example.imdb.exception.EntityNotFoundException;
import com.example.imdb.model.Movie;
import com.example.imdb.model.Person;
import com.example.imdb.model.requests.MovieRequest;
import com.example.imdb.model.responses.MovieResponse;
import com.example.imdb.model.responses.PersonResponse;
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

    public MovieResponse updateMovie(String titleId, MovieRequest request) {
        Movie movie = checkMovieId(titleId);

        if(request.getIsAdult()) movie.setIsAdult(request.getIsAdult());
        if(request.getRuntimeMinutes() != null) movie.setRuntimeMinutes(request.getRuntimeMinutes());
        if(request.getGenres() != null) movie.setGenres(request.getGenres());
        if(request.getType() != null) movie.setType(request.getType());
        if(request.getStartYear() != null) movie.setStartYear(request.getStartYear());
        if(request.getEndYear() != null) movie.setEndYear(request.getEndYear());
        if(request.getDirectors() != null) movie.setDirectors(request.getDirectors());
        if(request.getTitle() != null) movie.setTitle(request.getTitle());
        if(request.getTitleId() != null) movie.setTitleId(request.getTitleId());

        return movieRepository.save(movie).response();
    }

    public void deleteMovie(String titleId) {
        movieRepository.deleteById(titleId);
    }

    public List<MovieResponse> getAllMovies() {
        return movieRepository.findAll().stream().map(Movie::response).toList();
    }

    public List<PersonResponse> getDirectorsMovieById(String titleId)
    {
        return checkMovieId(titleId).getDirectors().stream().map(Person::response).toList();
    }
    public List<PersonResponse> getActorMovieById(String titleId)
    {
        return checkMovieId(titleId).getActors().stream().map(Person::response).toList();
    }

    public MovieResponse getMovieById(String titleId) {
        return checkMovieId(titleId).response();
    }

    public Movie checkMovieId(String titleId) {
        Optional<Movie> loaded = movieRepository.findById(titleId);
        if (loaded.isEmpty())
            throw new EntityNotFoundException(Movie.class.getName(), titleId);
        return loaded.get();
    }
}
