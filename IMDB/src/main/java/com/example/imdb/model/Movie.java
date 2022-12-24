package com.example.imdb.model;

import com.example.imdb.model.requests.MovieRequest;
import com.example.imdb.model.responses.DirectorResponse;
import com.example.imdb.model.responses.MovieCommentResponse;
import com.example.imdb.model.responses.MovieResponse;
import com.example.imdb.model.responses.SeriesResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Movie {
    @OneToMany(mappedBy = "parent")
    List<SeriesEpisode> episodes;
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

    @OneToOne
    private Rating rating;

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

    public MovieResponse movieResponse() {

        return MovieResponse.builder()
                .titleId(titleId)
                .type(type)
                .title(title)
                .startYear(startYear)
                .endYear(endYear)
                .runtimeMinutes(runtimeMinutes)
                .genres(genres)
                .actors(actors.stream().map(Person::response).collect(Collectors.toSet()))
                .directors(directors.stream().toList().stream().map(Person::response).collect(Collectors.toSet()))
                .comments(comments.stream().map(Comment::response).toList())
                .isAdult(isAdult)
                .rating(rating.response())
                .seriesEpisodes(episodes.stream().map(SeriesEpisode::response).collect(Collectors.toList()))
                .build();
    }

    public SeriesResponse seriesResponse() {

        return SeriesResponse.builder()
                .titleId(titleId)
                .type(type)
                .title(title)
                .startYear(startYear)
                .endYear(endYear)
                .runtimeMinutes(runtimeMinutes)
                .genres(genres)
                .actors(actors.stream().map(Person::response).collect(Collectors.toSet()))
                .directors(directors.stream().toList().stream().map(Person::response).collect(Collectors.toSet()))
                .comments(comments.stream().map(Comment::response).toList())
                .isAdult(isAdult)
                .rating(rating.response())
                .episodes(episodes.stream().map(SeriesEpisode::episodeResponse).collect(Collectors.toList()))
                .build();
    }

    public DirectorResponse directorResponse() {
        return DirectorResponse.builder()
                .titleId(titleId)
                .title(title)
                .directors(directors)
                .build();
    }

    public MovieCommentResponse commentResponse() {
        return MovieCommentResponse.builder()
                .titleId(titleId)
                .title(title)
                .type(type)
                .ratingResponse(rating.response())
                .build();
    }
}
