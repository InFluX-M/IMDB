package com.example.imdb.model.responses;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CommentMovieResponse {
    String body;
    String username;
}
