package com.example.imdb.repository;

import com.example.imdb.model.Movie;
import com.example.imdb.model.TitleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {

    List<Movie> findByTitleContaining(String title);

    List<Movie> findByType(TitleType type);

    List<Movie> findByDirectors_Name(String name);

    List<Movie> findByGenresContaining(String genre);

    List<Movie> findByStartYear(int year);

    List<Movie> findByEndYear(int year);

    List<Movie> findByRuntimeMinutes(int runtime);

    List<Movie> findByStartYearBetween(int start, int end);

    List<Movie> findByStartYearGreaterThan(int year);

    List<Movie> findByStartYearLessThan(int year);

    List<Movie> findByEndYearBetween(int start, int end);

    List<Movie> findByEndYearGreaterThan(int year);

    List<Movie> findByEndYearLessThan(int year);
}
