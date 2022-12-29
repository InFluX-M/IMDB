package com.example.imdb.model;

import com.example.imdb.model.requests.CommentRequest;
import com.example.imdb.model.responses.CommentMovieResponse;
import com.example.imdb.model.responses.CommentResponse;
import com.example.imdb.model.responses.ReplyResponse;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
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
    @ToString.Exclude
    private List<Comment> replies;

    public CommentRequest request() {
        return CommentRequest.builder()
                .body(body)
                .build();
    }

    public ReplyResponse replyResponse() {
        return ReplyResponse.builder()
                .username(user.getUsername())
                .body(body)
                .replies(replies.stream().map(Comment::replyResponse).toList())
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Comment comment = (Comment) o;
        return id != null && Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
