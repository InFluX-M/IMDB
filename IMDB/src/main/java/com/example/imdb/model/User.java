package com.example.imdb.model;

import com.example.imdb.model.requests.UserRequest;
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

    @OneToOne
    private MovieList watchList;

    @OneToMany(mappedBy = "user")
    private Set<MovieList> movieLists;

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
                .watchList(watchList.watchListResponse())
                .favoriteLists(movieLists.stream().map(MovieList::response).toList())
                .build();
    }
}
