package com.example.imdb.model;

import com.example.imdb.model.requests.SeriesEpisodeRequest;
import com.example.imdb.model.responses.EpisodeResponse;
import com.example.imdb.model.responses.SeriesEpisodeResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.stream.Collectors;

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

    public EpisodeResponse response() {
        return EpisodeResponse.builder()
//                .actors(episode.getActors().stream().map(Person::response).collect(Collectors.toSet()))
                .isAdult(episode.getIsAdult())
//                .rating(episode.getRating().response())
                .runtimeMinutes(episode.getRuntimeMinutes())
                .startYear(episode.getStartYear())
                .genres(episode.getGenres())
                .type(episode.getType())
                .title(episode.getTitle())
                .endYear(episode.getEndYear())
                .seasonNumber(seasonNumber)
                .episodeNumber(episodeNumber)
//                .parent(parent.commentResponse())
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
