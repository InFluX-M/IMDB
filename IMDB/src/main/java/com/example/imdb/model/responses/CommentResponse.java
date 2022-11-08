package com.example.imdb.model.responses;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class CommentResponse {

    String body;
    UserCommentResponse userCommentResponse;
    MovieCommentResponse movieCommentResponse;
    List<ReplyResponse> replies;
}
