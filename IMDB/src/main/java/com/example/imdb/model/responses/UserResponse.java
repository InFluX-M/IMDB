package com.example.imdb.model.responses;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class UserResponse {

    String username;
    WatchListResponse watchList;
    List<MovieListResponse> favoriteLists;

}
