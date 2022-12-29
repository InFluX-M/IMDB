package com.example.imdb.model;

import com.example.imdb.model.responses.EpisodeResponse;
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

    public EpisodeResponse response() {
        return EpisodeResponse.builder()
                .parentId(parent.getTitleId())
                .titleId(episode.getTitleId())
                .comments(episode.getComments().stream().map(Comment::response).collect(Collectors.toList()))
                .seasonNumber(seasonNumber)
                .episodeNumber(episodeNumber)
                .actors(episode.getActors().stream().map(Person::response).collect(Collectors.toSet()))
                .rating(episode.getRating().response())
                .runtimeMinutes(episode.getRuntimeMinutes())
                .build();
    }
}
