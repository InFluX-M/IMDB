package com.example.imdb.model.responses;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RatingCommentResponse {
    Float avgRating;
    Integer numVotes;
}
