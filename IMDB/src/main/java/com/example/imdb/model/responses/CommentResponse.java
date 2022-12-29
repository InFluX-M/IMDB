package com.example.imdb.model.responses;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class CommentResponse {

    String body;
    String username;
    MovieInformationResponse movieInformationResponse;
    List<ReplyResponse> replies;
}
