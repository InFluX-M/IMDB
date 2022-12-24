package com.example.imdb.repository;

import com.example.imdb.model.Movie;
import com.example.imdb.model.SeriesEpisode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesEpisodeRepo extends JpaRepository<SeriesEpisode, Long> {
    SeriesEpisode findByEpisode(Movie episode);
}
