package com.example.imdb.model;

import com.example.imdb.model.responses.WatchListResponse;
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
public class WatchList {

    @Id
    @GeneratedValue
    private String id;

    private int size;

    @ManyToMany
    @JoinTable(
            name = "movies",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "titleId")
    )
    private List<Movie> movies;

    public WatchListResponse response() {
        return WatchListResponse.builder()
                .size(size)
                .movies(movies.stream().map(Movie::commentResponse).toList())
                .build();
    }
}
