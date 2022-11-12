package com.example.imdb.model.responses;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class SeriesEpisodeResponse {

    MovieCommentResponse parent;
    MovieResponse episode;
    Integer seasonNumber;
    Integer episodeNumber;
}
