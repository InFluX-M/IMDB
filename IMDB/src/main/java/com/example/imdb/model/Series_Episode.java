package com.example.imdb.model;

import com.example.imdb.model.requests.SeriesEpisodeRequest;
import com.example.imdb.model.responses.SeriesEpisodeResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Series_Episode {
    @Id
    @GeneratedValue
    private long id;
    private String parentId;
    private String titleId;
    private int seasonNumber;
    private int episodeNumber;

    public SeriesEpisodeRequest request() {
        return SeriesEpisodeRequest.builder()
                .parentId(parentId)
                .titleId(titleId)
                .seasonNumber(seasonNumber)
                .episodeNumber(episodeNumber)
                .build();
    }

    public SeriesEpisodeResponse response() {
        return SeriesEpisodeResponse.builder()
                .parentId(parentId)
                .titleId(titleId)
                .seasonNumber(seasonNumber)
                .episodeNumber(episodeNumber)
                .build();
    }
}
