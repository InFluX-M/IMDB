package com.example.imdb.model;

import com.example.imdb.model.requests.UserRequest;
import com.example.imdb.model.responses.UserCommentResponse;
import com.example.imdb.model.responses.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany(mappedBy = "user")
    private Set<FavoriteList> favoriteLists;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    public UserRequest request() {
        return UserRequest.builder()
                .username(username)
                .password(password)
                .build();
    }

    public UserResponse response() {
        return UserResponse.builder()
                .username(username)
                .watchList(watchList)
                .comments(comments)
                .build();
    }

    public UserCommentResponse commentResponse() {
        return UserCommentResponse.builder()
                .username(username)
                .build();
    }
}
