package com.example.imdb.service;

import com.example.imdb.exception.EntityNotFoundException;
import com.example.imdb.model.Movie;
import com.example.imdb.model.Person;
import com.example.imdb.model.TitleType;
import com.example.imdb.model.requests.MovieRequest;
import com.example.imdb.model.responses.MovieResponse;
import com.example.imdb.model.responses.PersonResponse;
import com.example.imdb.model.responses.RatingResponse;
import com.example.imdb.repository.MovieRepository;
import com.example.imdb.repository.RatingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MovieService {

    private MovieRepository movieRepository;
    private RatingRepository ratingRepository;

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

        if (request.getIsAdult()) movie.setIsAdult(request.getIsAdult());
        if (request.getRuntimeMinutes() != null) movie.setRuntimeMinutes(request.getRuntimeMinutes());
        if (request.getGenres() != null) movie.setGenres(request.getGenres());
        if (request.getType() != null) movie.setType(request.getType());
        if (request.getStartYear() != null) movie.setStartYear(request.getStartYear());
        if (request.getEndYear() != null) movie.setEndYear(request.getEndYear());
        if (request.getDirectors() != null) movie.setDirectors(request.getDirectors());
        if (request.getTitle() != null) movie.setTitle(request.getTitle());
        if (request.getTitleId() != null) movie.setTitleId(request.getTitleId());

        return movieRepository.save(movie).response();
    }

    public void deleteMovie(String titleId) {
        movieRepository.deleteById(titleId);
    }

    public List<MovieResponse> getAllMovies() {
        return movieRepository.findAll().stream().map(Movie::response).toList();
    }

    public List<PersonResponse> getDirectorsMovieById(String titleId) {
        return checkMovieId(titleId).getDirectors().stream().map(Person::response).toList();
    }

    public List<PersonResponse> getActorMovieById(String titleId) {
        return checkMovieId(titleId).getActors().stream().map(Person::response).toList();
    }

    public MovieResponse getMovieById(String titleId) {
        return checkMovieId(titleId).response();
    }

    public List<MovieResponse> findByTitleContaining(String title) {
        return movieRepository.findByTitleContaining(title).stream().map(Movie::response).toList();
    }

    public List<MovieResponse> findByType(String type) {
        return movieRepository.findByType(TitleType.valueOf(type.toUpperCase())).stream().map(Movie::response).toList();
    }

    public List<MovieResponse> findByDirectors_Name(String name) {
        return movieRepository.findByDirectors_Name(name).stream().map(Movie::response).toList();
    }

    public List<MovieResponse> findByGenresContaining(String genre) {
        return movieRepository.findByGenresContaining(genre).stream().map(Movie::response).toList();
    }

    public List<MovieResponse> findByStartYear(int year) {
        return movieRepository.findByStartYear(year).stream().map(Movie::response).toList();
    }

    public List<MovieResponse> findByEndYear(int year) {
        return movieRepository.findByEndYear(year).stream().map(Movie::response).toList();
    }

    public List<MovieResponse> findByRuntimeMinutes(int min) {
        return movieRepository.findByRuntimeMinutes(min).stream().map(Movie::response).toList();
    }

    public List<MovieResponse> findByStartYearBetween(int start, int end) {
        return movieRepository.findByStartYearBetween(start, end).stream().map(Movie::response).toList();
    }

    public List<MovieResponse> findByStartYearGreaterThan(int year) {
        return movieRepository.findByStartYearGreaterThan(year).stream().map(Movie::response).toList();
    }

    public List<MovieResponse> findByStartYearLessThan(int year) {
        return movieRepository.findByStartYearLessThan(year).stream().map(Movie::response).toList();
    }

    public List<MovieResponse> findByEndYearBetween(int start, int end) {
        return movieRepository.findByEndYearBetween(start, end).stream().map(Movie::response).toList();
    }

    public List<MovieResponse> findByEndYearGreaterThan(int year) {
        return movieRepository.findByEndYearGreaterThan(year).stream().map(Movie::response).toList();
    }

    public List<MovieResponse> findByEndYearLessThan(int year) {
        return movieRepository.findByEndYearLessThan(year).stream().map(Movie::response).toList();
    }

    public List<MovieResponse> findByActor(String id) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/imdb", "root", "")) {

            Class.forName("com.mysql.cj.jdbc.Driver");

            String sql = "select * from movie " +
                    "inner join movie_actor " +
                    "on movie.title_id = movie_actor.title_id " +
                    "where movie_actor.id='" + id + '\'';

            Statement s = connection.prepareStatement(sql);
            ResultSet rs = s.executeQuery(sql);

            List<String> titles = new ArrayList<>();
            List<MovieResponse> movies = new ArrayList<>();

            while (rs.next())
                titles.add(rs.getString("title_id"));

            for (String t : titles)
                movies.add(checkMovieId(t).response());

            return movies;

        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<PersonResponse> getActors(String titleId) {
        Movie movie = checkMovieId(titleId);
        return movie.getActors().stream().map(Person::response).toList();
    }

    public List<PersonResponse> getDirectors(String titleId) {
        Movie movie = checkMovieId(titleId);
        return movie.getDirectors().stream().map(Person::response).toList();
    }

    public RatingResponse getRating(String titleId) {
        Movie movie = checkMovieId(titleId);
        return movie.getRating().response();
    }

    public Movie checkMovieId(String titleId) {
        Optional<Movie> loaded = movieRepository.findById(titleId);
        if (loaded.isEmpty())
            throw new EntityNotFoundException(Movie.class.getName(), titleId);
        return loaded.get();
    }

}
