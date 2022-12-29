package com.example.imdb.controller;

import com.example.imdb.dto.UserDataDTO;
import com.example.imdb.dto.UserResponseDTO;
import com.example.imdb.model.User;
import com.example.imdb.model.requests.FavoriteListRequest;
import com.example.imdb.model.responses.*;
import com.example.imdb.service.UserService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final ModelMapper modelMapper;
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/rate/{titleId}/{rating}")
    public RatingResponse rateMovie(@PathVariable String titleId,
                                    @PathVariable Integer rating) {
        return userService.rateMovie(titleId, rating);
    }

    @PostMapping("/fav-lists")
    public ResponseEntity<MovieListResponse> addFavLists(@RequestBody FavoriteListRequest request) {
        return new ResponseEntity<>(userService.addFavList(request), HttpStatus.OK);
    }

    @PostMapping("/fav-lists/{list}/{titleId}")
    public ResponseEntity<MovieListResponse> addToFavList(@PathVariable String list,
                                                          @PathVariable String titleId) {
        return new ResponseEntity<>(userService.addToFavList(list, titleId), HttpStatus.OK);
    }

    @GetMapping("/fav-lists")
    public ResponseEntity<List<AllMovieListResponse>> getFavLists() {
        return new ResponseEntity<>(userService.getFavLists(), HttpStatus.OK);
    }

    @GetMapping("/fav-lists/{list}")
    public ResponseEntity<MovieListResponse> getFavLists(@PathVariable String list) {
        return new ResponseEntity<>(userService.getFavList(list), HttpStatus.OK);
    }

    @PostMapping("/watch-list/{titleId}")
    public ResponseEntity<WatchListResponse> addToWatchList(@PathVariable String titleId) {
        return new ResponseEntity<>(userService.addToWatchList(titleId), HttpStatus.OK);
    }

    @GetMapping("/watch-list")
    public ResponseEntity<WatchListResponse> getWatchList() {
        return new ResponseEntity<>(userService.getWatchList(), HttpStatus.OK);
    }

    @PostMapping("/signin")
    @ApiOperation(value = "${JwtController.signin}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    public String login(//
                        @ApiParam("Username") @RequestParam(value = "username") String username, //
                        @ApiParam("Password") @RequestParam(value = "password") String password) {
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

    @GetMapping("/logout")
    public void logout() {
        userService.logout();
    }

}
