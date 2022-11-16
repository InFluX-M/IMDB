package com.example.imdb.model;

import com.example.imdb.model.requests.FavoriteListRequest;
import com.example.imdb.model.responses.MovieListResponse;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Getter
@Setter
public class MovieList {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private int size;

    @ManyToMany
    @JoinTable(
            name = "movieList_movies",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "titleId")
    )
    private List<Movie> movies;

    @ManyToOne
    private User user;

    public FavoriteListRequest request() {
        return FavoriteListRequest.builder()
                .name(name)
                .build();
    }

    public MovieListResponse response() {
        return MovieListResponse.builder()
                .name(name)
                .size(size)
                .movies(movies.stream().map(Movie::commentResponse).toList())
                .username(user.getUsername())
                .build();
    }
}
