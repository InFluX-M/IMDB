package com.example.imdb.model;

import com.example.imdb.model.requests.MovieRequest;
import com.example.imdb.model.responses.DirectorResponse;
import com.example.imdb.model.responses.MovieResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Movie {
    @Id
    private String titleId;
    private TitleType type;
    private String title;
    private Boolean isAdult;
    private Integer startYear;
    private Integer endYear;
    private Integer runtimeMinutes;
    private String genres;
    @Transient
    private List<String> genresList;

    @OneToMany(mappedBy = "parent")
    List<SeriesEpisode> episodes;

    @ManyToMany
    @JoinTable(
            name = "movie_director",
            joinColumns = @JoinColumn(name = "titleId"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private Set<Person> directors;

    @ManyToMany
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(name = "titleId"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private Set<Person> actors;

    @OneToMany(mappedBy = "movie")
    private List<Comment> comments;

    public MovieRequest request() {
        return MovieRequest.builder()
                .titleId(titleId)
                .type(type)
                .title(title)
                .startYear(startYear)
                .endYear(endYear)
                .runtimeMinutes(runtimeMinutes)
                .genres(genres)
                .directors(directors)
                .isAdult(isAdult)
                .build();
    }

    public MovieResponse response() {
        return MovieResponse.builder()
                .titleId(titleId)
                .type(type)
                .title(title)
                .startYear(startYear)
                .endYear(endYear)
                .runtimeMinutes(runtimeMinutes)
                .genres(genres)
                .actors(actors)
                .directors(directors)
                .comments(comments)
                .isAdult(isAdult)
                .episodeResponses(episodes.stream().map(SeriesEpisode::episodeResponse).toList())
                .build();
    }

    public DirectorResponse directorResponse() {
        return DirectorResponse.builder()
                .titleId(titleId)
                .title(title)
                .directors(directors)
                .build();
    }
}
