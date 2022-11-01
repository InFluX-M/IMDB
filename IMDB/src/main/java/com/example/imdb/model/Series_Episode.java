package com.example.imdb.model;

import javax.persistence.*;

@Entity
@Table
public class Series_Episode {
    @Id
    @GeneratedValue
    private long id;
    private String parentId;
    private String titleId;
    private int seasonNumber;
    private int episodeNumber;
}
