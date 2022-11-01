package com.example.imdb.model;

import jdk.jfr.Enabled;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table
public class Rating {
    @Id
    @GeneratedValue
    private int id;
    private String titleId;
    private float avgRating;
    private int numVotes;
}
