package com.example.imdb.model;

import com.example.imdb.model.responses.CommentResponse;
import com.example.imdb.model.responses.ReplyResponse;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
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
                .movieInformationResponse(movie.informationResponse())
                .username(user.getUsername())
                .replies(replies.stream().map(Comment::replyResponse).toList())
                .build();
    }
}
