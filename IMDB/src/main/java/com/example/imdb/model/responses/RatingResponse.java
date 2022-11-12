package com.example.imdb.model.responses;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class RatingResponse {

    Float avgRating;
    Integer numVotes;
}
