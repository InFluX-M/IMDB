package com.example.imdb.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Comment {

    @Id
    @GeneratedValue
    private int id;
    private String body;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    @ManyToOne
    @JoinColumn(name = "titleId")
    private Movie movie;

}
