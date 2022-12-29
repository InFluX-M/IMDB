package com.example.imdb.model.responses;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class MovieListResponse {

    String username;
    String name;
    Integer size;
    List<MovieInformationResponse> movies;
}
