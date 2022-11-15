package com.example.imdb.controller;

import com.example.imdb.model.User;
import com.example.imdb.model.requests.FavoriteListRequest;
import com.example.imdb.model.requests.UserRequest;
import com.example.imdb.model.responses.MovieListResponse;
import com.example.imdb.model.responses.MovieResponse;
import com.example.imdb.model.responses.PersonResponse;
import com.example.imdb.model.responses.UserResponse;
import com.example.imdb.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest request) {
        return new ResponseEntity<>(userService.addUser(request), HttpStatus.CREATED);
    }

    @PutMapping("/users/{username}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable String username,
                                                   @RequestBody UserRequest request) {
        return new ResponseEntity<>(userService.updateUser(username, request), HttpStatus.OK);
    }

    @DeleteMapping("/users/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getUser() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String username) {
        return new ResponseEntity<>(userService.getUserById(username), HttpStatus.OK);
    }

    @GetMapping("/users/{username}/fav-list")
    public ResponseEntity<List<MovieListResponse>> getFavLists(@PathVariable String username) {
        return new ResponseEntity<>(userService.getFavLists(username), HttpStatus.OK);
    }

    @GetMapping("/users/{username}/watch-list")
    public ResponseEntity<Set<MovieResponse>> getWatchList(@PathVariable String username) {
        return new ResponseEntity<>(userService.getWatchList(username), HttpStatus.OK);
    }

}
