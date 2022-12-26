package com.example.imdb.service;

import com.example.imdb.exception.CustomException;
import com.example.imdb.exception.EntityNotFoundException;
import com.example.imdb.exception.InvalidRatingException;
import com.example.imdb.model.*;
import com.example.imdb.model.requests.FavoriteListRequest;
import com.example.imdb.model.responses.*;
import com.example.imdb.repository.*;
import com.example.imdb.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private RatingRepository ratingRepository;
    private MovieListRepository movieListRepository;
    private MovieRepository movieRepository;
    private UserRatingRepository userRatingRepository;
    private WatchListRepository watchListRepository;

    private User nowUser;

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(User::response).toList();
    }

    public List<MovieListResponse> getFavLists() {
        return checkUsername(nowUser.getUsername()).getFavLists().stream().map(MovieList::response).toList();
    }

    public WatchListResponse getWatchList() {
        return checkUsername(nowUser.getUsername()).getWatchList().response();

    }

    public MovieListResponse addFavList(FavoriteListRequest request) {

        User user = checkUsername(nowUser.getUsername());

        MovieList list = MovieList.builder()
                .size(0)
                .name(request.name())
                .user(user)
                .build();

        return movieListRepository.save(list).response();
    }
    public MovieListResponse addToFavList(String listName, String titleId) {

        Movie movie = checkMovieId(titleId);
        MovieList list = movieListRepository.getByNameAndUser_Username(listName, nowUser.getUsername());
        list.getMovies().add(movie);
        return movieListRepository.save(list).response();
    }

    public WatchListResponse addToWatchList(String titleId) {

        User user = checkUsername(nowUser.getUsername());
        Movie movie = checkMovieId(titleId);

        user.getWatchList().getMovies().add(movie);
        userRepository.save(user);
        watchListRepository.save(user.getWatchList());

        return user.getWatchList().response();
    }

    public RatingResponse rateMovie(String titleId, Integer rating) {

        if (rating < 1 || rating > 10)
            throw new InvalidRatingException();

        Movie movie = checkMovieId(titleId);
        Rating ratingObj = movie.getRating();

        float newAvg = (rating + (ratingObj.getAvgRating() * ratingObj.getNumVotes())) / (ratingObj.getNumVotes() + 1);
        ratingObj.setAvgRating(newAvg);
        ratingObj.setNumVotes(ratingObj.getNumVotes() + 1);

        UserRating userRating = UserRating.builder()
                .rating(rating)
                .titleId(titleId)
                .userId(nowUser.getUsername())
                .build();

        userRatingRepository.save(userRating);

        return ratingRepository.save(ratingObj).response();
    }

    public User checkUsername(String username) {
        User user = userRepository.findByUsername(username);
//        if (loaded.isEmpty())
//            throw new EntityNotFoundException(User.class.getName(), username);
//        return loaded.get();
        return user;
    }

    public Movie checkMovieId(String titleId) {
        Optional<Movie> loaded = movieRepository.findById(titleId);
        if (loaded.isEmpty())
            throw new EntityNotFoundException(Movie.class.getName(), titleId);
        return loaded.get();
    }

    //---------------------------------------------------------------
    //JWT
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public String signin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            nowUser = checkUsername(username);
            return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getUserRoles());
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String signup(User appUser) {
        if (!userRepository.existsByUsername(appUser.getUsername())) {
            appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
            userRepository.save(appUser);
            nowUser = checkUsername(appUser.getUsername());
            return jwtTokenProvider.createToken(appUser.getUsername(), appUser.getUserRoles());
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public void delete(String username) {
        userRepository.deleteByUsername(username);
    }

    public User search(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
        }
        return user;
    }

    public User whoami(HttpServletRequest req) {
        return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
    }

    public String refresh(String username) {
        return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getUserRoles());
    }


}
