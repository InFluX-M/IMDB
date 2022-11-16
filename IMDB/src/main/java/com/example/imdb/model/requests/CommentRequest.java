package com.example.imdb.model.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder
public record CommentRequest(String body) {

}
