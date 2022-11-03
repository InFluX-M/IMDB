package com.example.imdb.model;

import com.example.imdb.model.requests.FavoriteListRequest;
import com.example.imdb.model.responses.FavoriteListResponse;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table
public class FavoriteList {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int size;

    @ManyToMany
    @JoinTable(
            name = "movie_list",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "titleId")
    )
    private List<Movie> movies;

    public FavoriteListRequest request() {
        return FavoriteListRequest.builder()
                .name(name)
                .size(size)
                .build();
    }

    public FavoriteListResponse response() {
        return FavoriteListResponse.builder()
                .name(name)
                .size(size)
                .movies(movies)
                .build();
    }
}
