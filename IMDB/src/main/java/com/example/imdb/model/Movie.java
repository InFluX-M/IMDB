package com.example.imdb.model;

import com.example.imdb.model.responses.DirectorResponse;
import com.example.imdb.model.responses.MovieInformationResponse;
import com.example.imdb.model.responses.MovieResponse;
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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "movie_director",
            joinColumns = @JoinColumn(name = "titleId"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private Set<Person> directors;
    @ManyToMany(fetch = FetchType.EAGER)
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
                .episodes(episodes.stream().map(SeriesEpisode::response).collect(Collectors.toList())) // todo hazfe in?
                .build();
    }

    public DirectorResponse directorResponse() {
        return DirectorResponse.builder()
                .titleId(titleId)
                .title(title)
                .directors(directors.stream().map(Person::response).collect(Collectors.toSet()))
                .build();
    }

    public MovieInformationResponse informationResponse() {
        return MovieInformationResponse.builder()
                .titleId(titleId)
                .title(title)
                .type(type)
                .ratingResponse(rating.response())
                .build();
    }
}
