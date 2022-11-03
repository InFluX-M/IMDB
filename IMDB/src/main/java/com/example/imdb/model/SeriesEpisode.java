package com.example.imdb.model;

import com.example.imdb.model.requests.SeriesEpisodeRequest;
import com.example.imdb.model.responses.EpisodeResponse;
import com.example.imdb.model.responses.SeriesEpisodeResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeriesEpisode {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private Movie parent;
    private String titleId; // todo Movie
    private int seasonNumber;
    private int episodeNumber;

    public SeriesEpisodeRequest request() {
        return SeriesEpisodeRequest.builder()
                .titleId(titleId)
                .seasonNumber(seasonNumber)
                .episodeNumber(episodeNumber)
                .build();
    }

    public SeriesEpisodeResponse response() {
        return SeriesEpisodeResponse.builder()
                .titleId(titleId)
                .seasonNumber(seasonNumber)
                .episodeNumber(episodeNumber)
                .parent(parent.response())
                .build();
    }

    public EpisodeResponse episodeResponse() {
        return EpisodeResponse.builder()
                .titleId(titleId)
                .seasonNumber(seasonNumber)
                .episodeNumber(episodeNumber)
                .build();
    }
}
