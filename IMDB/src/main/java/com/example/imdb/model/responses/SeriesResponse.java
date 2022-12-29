package com.example.imdb.model.responses;

import com.example.imdb.model.TitleType;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.Set;

@Builder
@Value
public class SeriesResponse {

    String titleId;
    TitleType type;
    String title;
    Boolean isAdult;
    Integer startYear;
    Integer endYear;
    Integer runtimeMinutes;
    String genres;
    List<EpisodeResponse> episodes;
    Set<PersonResponse> directors;
    Set<PersonResponse> actors;
    List<CommentResponse> comments;
    RatingResponse rating;
}
