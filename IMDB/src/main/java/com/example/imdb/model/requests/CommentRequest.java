package com.example.imdb.model.requests;

import lombok.Builder;

@Builder
public record CommentRequest(String body) {
}
