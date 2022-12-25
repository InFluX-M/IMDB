package com.example.imdb.controller;

import com.example.imdb.dto.UserDataDTO;
import com.example.imdb.dto.UserResponseDTO;
import com.example.imdb.model.User;
import com.example.imdb.model.requests.FavoriteListRequest;
import com.example.imdb.model.requests.UserRequest;
import com.example.imdb.model.responses.MovieCommentResponse;
import com.example.imdb.model.responses.MovieListResponse;
import com.example.imdb.model.responses.RatingResponse;
import com.example.imdb.model.responses.UserResponse;
import com.example.imdb.service.UserService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private UserService userService;


    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
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

    //--------------------------------------------------------
    //JWT
    private final ModelMapper modelMapper;

    @PostMapping("/signin")
    @ApiOperation(value = "${JwtController.signin}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    public String login(//
                        @ApiParam("Username") @RequestParam String username, //
                        @ApiParam("Password") @RequestParam String password) {
        return userService.signin(username, password);
    }

    @PostMapping("/signup")
    @ApiOperation(value = "${JwtController.signup}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 422, message = "Username is already in use")})
    public String signup(@RequestBody UserDataDTO user) {
        return userService.signup(modelMapper.map(user, User.class));
    }

    @DeleteMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${JwtController.delete}", authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "The user doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public String delete(@ApiParam("Username") @PathVariable String username) {
        userService.delete(username);
        return username;
    }

    @GetMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${JwtController.search}", response = UserResponseDTO.class, authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "The user doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public UserResponseDTO search(@ApiParam("Username") @PathVariable String username) {
        return modelMapper.map(userService.search(username), UserResponseDTO.class);
    }

    @GetMapping(value = "/me")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @ApiOperation(value = "${JwtController.me}", response = UserResponseDTO.class, authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public UserResponseDTO whoami(HttpServletRequest req) {
        return modelMapper.map(userService.whoami(req), UserResponseDTO.class);
    }

    @GetMapping("/refresh")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public String refresh(HttpServletRequest req) {
        return userService.refresh(req.getRemoteUser());
    }

}
