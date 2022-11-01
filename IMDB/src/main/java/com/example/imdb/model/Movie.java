package com.example.imdb.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
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

    @ManyToMany
    @JoinTable(
            name = "movie_director",
            joinColumns = @JoinColumn(name = "titleId"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private Set<Person> directors;

    @OneToMany(mappedBy = "movie")
    private List<Comment> comments;

}
