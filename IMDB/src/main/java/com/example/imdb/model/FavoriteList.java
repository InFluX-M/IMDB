package com.example.imdb.model;

import com.example.imdb.model.requests.FavoriteListRequest;
import com.example.imdb.model.responses.FavoriteListResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class FavoriteList {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private int size;

    @ManyToMany
    @JoinTable(
            name = "movie_list",
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

    public FavoriteListResponse response() {
        return FavoriteListResponse.builder()
                .name(name)
                .size(size)
                .movies(movies)
                .build();
    }
}
