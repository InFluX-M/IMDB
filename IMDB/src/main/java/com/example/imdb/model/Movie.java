package com.example.imdb.model;

import com.example.imdb.model.requests.MovieRequest;
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
    private boolean isAdult;
    private int startYear;
    private int endYear;
    private int runtimeMinutes;
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
                .directors(directors)
                .comments(comments)
                .isAdult(isAdult)
                .episodeResponses(episodes.stream().map(SeriesEpisode::episodeResponse).toList())
                .build();
    }
}
