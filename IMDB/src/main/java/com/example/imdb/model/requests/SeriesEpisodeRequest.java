package com.example.imdb.model.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Builder
@Value
@Getter
public class SeriesEpisodeRequest {

    String parentId;
    String titleId;
    Integer seasonNumber;
    Integer episodeNumber;
}
