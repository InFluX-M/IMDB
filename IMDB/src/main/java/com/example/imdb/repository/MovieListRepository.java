package com.example.imdb.repository;

import com.example.imdb.model.MovieList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieListRepository extends JpaRepository<MovieList, Integer> {
    MovieList getByName(String name);
}
