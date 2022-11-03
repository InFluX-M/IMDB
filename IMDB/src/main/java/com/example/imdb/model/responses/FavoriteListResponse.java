package com.example.imdb.model.responses;

import com.example.imdb.model.Movie;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class FavoriteListResponse {

    String name;
    int size;
    List<Movie> movies;
}
