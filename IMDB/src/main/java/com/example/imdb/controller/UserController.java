package com.example.imdb.controller;

import com.example.imdb.model.requests.FavoriteListRequest;
import com.example.imdb.model.requests.UserRequest;
import com.example.imdb.model.responses.MovieCommentResponse;
import com.example.imdb.model.responses.MovieListResponse;
import com.example.imdb.model.responses.RatingResponse;
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
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest request) {
        return new ResponseEntity<>(userService.addUser(request), HttpStatus.CREATED);
    }

    @PutMapping("/{username}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable String username,
                                                   @RequestBody UserRequest request) {
        return new ResponseEntity<>(userService.updateUser(username, request), HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String username) {
        return new ResponseEntity<>(userService.getUserById(username), HttpStatus.OK);
    }

    @PostMapping("/rate/{titleId}/{rating}")
    public RatingResponse rateMovie(@PathVariable String titleId,
                                    @PathVariable Integer rating) {
        // todo user lazeme?
        return userService.rateMovie(titleId, rating);
    }

    // fav list ---------------------------------------------------------------------------------------

    @PostMapping("/{username}/fav-lists")
    public ResponseEntity<MovieListResponse> addFavLists(@RequestBody FavoriteListRequest request,
                                                         @PathVariable String username) {
        // todo kodoom user?!
        // todo nemire to in
        System.err.println("!::");
        return new ResponseEntity<>(userService.addFavList(username, request), HttpStatus.OK);
    }

    @PostMapping("/{username}/fav-lists/{list}/{titleId}")
    public ResponseEntity<MovieListResponse> addToFavList(@PathVariable String username,
                                                          @PathVariable String list,
                                                          @PathVariable String titleId) {
        // todo kodoom user?!
        return new ResponseEntity<>(userService.addToFavList(list, titleId), HttpStatus.OK);
    }

    @GetMapping("/{username}/fav-lists")
    public ResponseEntity<List<MovieListResponse>> getFavLists(@PathVariable String username) {
        return new ResponseEntity<>(userService.getFavLists(username), HttpStatus.OK);
    }

    // watch list -------------------------------------------------------------------------------------

    @PostMapping("/{username}/watch-list/{titleId}")
    public ResponseEntity<List<MovieCommentResponse>> addToWatchList(@PathVariable String username,
                                                                     @PathVariable String titleId) {
        return new ResponseEntity<>(userService.addToWatchList(username, titleId), HttpStatus.OK);
    }

    @GetMapping("/{username}/watch-list")
    public ResponseEntity<Set<MovieCommentResponse>> getWatchList(@PathVariable String username) {
        return new ResponseEntity<>(userService.getWatchList(username), HttpStatus.OK);
    }

}
