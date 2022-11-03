package com.example.imdb.model;

import com.example.imdb.model.requests.CommentRequest;
import com.example.imdb.model.responses.CommentResponse;
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

    public CommentRequest request() {
        return CommentRequest.builder()
                .body(body)
                .build();
    }

    public CommentResponse response() {
        return CommentResponse.builder()
                .body(body)
                .build();
    }
}
