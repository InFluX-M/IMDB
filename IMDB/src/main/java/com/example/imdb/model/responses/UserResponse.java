package com.example.imdb.model.responses;

import com.example.imdb.model.Comment;
import com.example.imdb.model.Movie;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.Set;

@Builder
@Value
public class UserResponse {

    String username;
    Set<Movie> watchList;
    List<Comment> comments;
}
