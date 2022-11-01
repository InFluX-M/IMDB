package com.example.imdb.model;

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
}
