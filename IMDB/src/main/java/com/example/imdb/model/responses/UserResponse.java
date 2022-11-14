package com.example.imdb.model.responses;

import com.example.imdb.model.Comment;
import com.example.imdb.model.Movie;
import com.example.imdb.model.MovieList;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.Set;

@Builder
@Value
public class UserResponse {

    String username;
    WatchListResponse watchList;
    List<MovieListResponse> favoriteLists;

}
