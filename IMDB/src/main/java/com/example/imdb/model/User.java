package com.example.imdb.model;

import com.example.imdb.model.responses.UserResponse;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class User {

    @ElementCollection(fetch = FetchType.EAGER)
    List<UserRole> userRoles;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @OneToOne
    private WatchList watchList;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private Set<MovieList> favLists;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Comment> comments;

    public UserResponse response() {
        return UserResponse.builder()
                .username(username)
                .watchList(watchList.response())
                .favoriteLists(favLists.stream().map(MovieList::response).toList())
                .build();
    }
}
