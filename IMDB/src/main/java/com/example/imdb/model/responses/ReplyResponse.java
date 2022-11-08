package com.example.imdb.model.responses;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ReplyResponse {
    String body;
}
