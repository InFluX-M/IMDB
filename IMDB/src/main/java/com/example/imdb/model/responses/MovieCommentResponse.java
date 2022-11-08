package com.example.imdb.model.responses;

import com.example.imdb.model.TitleType;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MovieCommentResponse {
    String titleId;
    TitleType type;
    String title;
    RatingResponse ratingResponse;
}
