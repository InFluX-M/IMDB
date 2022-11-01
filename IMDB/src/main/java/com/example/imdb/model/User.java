package com.example.imdb.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table
public class User {
    @Id
    private String username;
    private String password;

    @ManyToMany
    @JoinTable(
            name = "watch_list",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "titleId")
    )
    private Set<Movie> watchList;

    @ManyToMany
    @JoinTable(
            name = "favorite_lists",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private Set<FavoriteList> favoriteLists;

}
