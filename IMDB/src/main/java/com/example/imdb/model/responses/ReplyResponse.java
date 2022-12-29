package com.example.imdb.model.responses;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class ReplyResponse {
    String username;
    String body;
    List<ReplyResponse> replies;
}
