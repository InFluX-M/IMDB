package com.example.imdb.model.responses;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class WatchListResponse {

    Integer size;
    List<MovieCommentResponse> movies;
}
