package com.example.imdb.model.responses;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class SeriesEpisodeResponse {

    MovieResponse parent;
    String titleId;
    int seasonNumber;
    int episodeNumber;
}
