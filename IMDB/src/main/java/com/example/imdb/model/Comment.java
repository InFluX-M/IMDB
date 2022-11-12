package com.example.imdb.model;

import com.example.imdb.model.requests.CommentRequest;
import com.example.imdb.model.responses.CommentMovieResponse;
import com.example.imdb.model.responses.CommentResponse;
import com.example.imdb.model.responses.ReplyResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Builder
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Comment implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private String body;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    @ManyToOne
    @JoinColumn(name = "titleId")
    private Movie movie;

    @ManyToOne
    private Comment parent;

    @OneToMany(mappedBy = "parent")
    private List<Comment> replies;

    public CommentRequest request() {
        return CommentRequest.builder()
                .body(body)
                .build();
    }

    public ReplyResponse replyResponse() {
        return ReplyResponse.builder()
                .body(body)
                .build();
    }

    public CommentResponse response() {
        return CommentResponse.builder()
                .body(body)
                .movieCommentResponse(movie.commentResponse())
                .username(user.getUsername())
                .replies(replies.stream().map(Comment::replyResponse).toList())
                .build();
    }

    public CommentMovieResponse movieResponse() {
        return CommentMovieResponse.builder()
                .body(body)
                .username(user.getUsername())
                .build();
    }

}
