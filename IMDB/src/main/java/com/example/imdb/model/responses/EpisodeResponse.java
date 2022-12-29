package com.example.imdb.model.responses;

import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.Set;

@Builder
@Value
public class EpisodeResponse {

    String parentId;
    Integer seasonNumber;
    Integer episodeNumber;
    String titleId;
    Integer runtimeMinutes;
    Set<PersonResponse> actors;
    List<CommentResponse> comments;
    RatingResponse rating;

}
