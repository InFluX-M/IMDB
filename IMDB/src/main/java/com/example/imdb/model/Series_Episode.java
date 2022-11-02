package com.example.imdb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Series_Episode {
    @Id
    @GeneratedValue
    private long id;
    private String parentId;
    private String titleId;
    private int seasonNumber;
    private int episodeNumber;
}
