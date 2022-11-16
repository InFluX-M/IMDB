package com.example.imdb.model.responses;

import com.example.imdb.model.TitleType;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.Set;

@Builder
@Value
public class MovieResponse {

    String titleId;
    TitleType type;
    String title;
    Boolean isAdult;
    Integer startYear;
    Integer endYear;
    Integer runtimeMinutes;
    String genres;
    Set<PersonResponse> directors;
    Set<PersonResponse> actors;
    List<CommentResponse> comments;
    List<EpisodeResponse> episode;
    RatingResponse rating;
}
