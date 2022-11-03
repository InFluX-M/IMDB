package com.example.imdb.model.requests;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class RatingRequest {

    String titleId;
    float avgRating;
    int numVotes;
}
