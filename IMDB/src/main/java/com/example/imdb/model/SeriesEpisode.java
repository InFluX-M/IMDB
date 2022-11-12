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
    private Long id;

    @ManyToOne
    private Movie parent;

    @OneToOne
    private Movie episode;

    private Integer seasonNumber;
    private Integer episodeNumber;

    public SeriesEpisodeRequest request() {
        return SeriesEpisodeRequest.builder()
                .titleId(episode.getTitleId())
                .seasonNumber(seasonNumber)
                .episodeNumber(episodeNumber)
                .build();
    }

    public SeriesEpisodeResponse response() {
        return SeriesEpisodeResponse.builder()
                .episode(episode.response())
                .seasonNumber(seasonNumber)
                .episodeNumber(episodeNumber)
                .parent(parent.commentResponse())
                .build();
    }

    public EpisodeResponse episodeResponse() {
        return EpisodeResponse.builder()
                .titleId(episode.getTitleId())
                .seasonNumber(seasonNumber)
                .episodeNumber(episodeNumber)
                .build();
    }
}
