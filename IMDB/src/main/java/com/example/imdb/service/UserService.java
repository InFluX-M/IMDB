package com.example.imdb.service;

import com.example.imdb.exception.EntityNotFoundException;
import com.example.imdb.exception.InvalidRatingException;
import com.example.imdb.exception.InvalidUsernameException;
import com.example.imdb.model.Movie;
import com.example.imdb.model.MovieList;
import com.example.imdb.model.Rating;
import com.example.imdb.model.User;
import com.example.imdb.model.requests.FavoriteListRequest;
import com.example.imdb.model.requests.UserRequest;
import com.example.imdb.model.responses.*;
import com.example.imdb.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private RatingRepository ratingRepository;
    private MovieListRepository movieListRepository;
    private MovieRepository movieRepository;

    public UserResponse addUser(UserRequest request) {
        // todo password validation?

        if (userRepository.findById(request.getUsername()).isPresent())
            throw new InvalidUsernameException(request.getUsername());

        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .watchList(new HashSet<>())
                .favLists(new HashSet<>())
                .build();

        return userRepository.save(user).response();
    }

    public UserResponse updateUser(String username, UserRequest request) {
        // todo valid pass?
        User user = checkUsername(username);

        if (request.getPassword() != null) user.setPassword(request.getPassword());

        return userRepository.save(user).response();
    }

    public void deleteUser(String username) {
        checkUsername(username);
        userRepository.deleteById(username);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(User::response).toList();
    }

    public UserResponse getUserById(String username) {
        return checkUsername(username).response();
    }

    public List<MovieListResponse> getFavLists(String username) {
        return userRepository.findById(username).get().getFavLists().stream().map(MovieList::response).toList();
    }

    public Set<MovieCommentResponse> getWatchList(String username) {
//   todo     return userRepository.findById(username).get().getWatchList().getMovies().stream().map(Movie::response).collect(Collectors.toSet());
        return checkUsername(username).getWatchList().stream().map(Movie::commentResponse).collect(Collectors.toSet());

    }

    public MovieListResponse addFavList(String username, FavoriteListRequest request) {

        User user = checkUsername(username);

        MovieList list = MovieList.builder()
                .size(0)
                .name(request.getName())
                .user(user)
                .build();

        return movieListRepository.save(list).response();
    }

    public MovieListResponse addToFavList(String listName, String titleId) {

        Movie movie = checkMovieId(titleId);
        MovieList list = movieListRepository.getByName(listName);

        list.getMovies().add(movie);

        return movieListRepository.save(list).response();
    }

    public List<MovieCommentResponse> addToWatchList(String username, String titleId) {

        User user = checkUsername(username);
        Movie movie = checkMovieId(titleId);

//        user.getWatchList().getMovies().add(movie); todo
        user.getWatchList().add(movie);
        userRepository.save(user);

//        return user.getWatchList().getMovies().stream().map(Movie::response).toList(); todo
        return user.getWatchList().stream().map(Movie::commentResponse).toList();
    }

    public RatingResponse rateMovie(String titleId, Integer rating) {

        if (rating < 1 || rating > 10)
            throw new InvalidRatingException();

        Movie movie = checkMovieId(titleId);
        Rating ratingObj = movie.getRating();

        float newAvg = (rating + (ratingObj.getAvgRating() * ratingObj.getNumVotes())) / (ratingObj.getNumVotes() + 1);
        ratingObj.setAvgRating(newAvg);
        ratingObj.setNumVotes(ratingObj.getNumVotes() + 1);

        return ratingRepository.save(ratingObj).response();
    }

    public User checkUsername(String username) {
        Optional<User> loaded = userRepository.findById(username);
        if (loaded.isEmpty())
            throw new EntityNotFoundException(User.class.getName(), username);
        return loaded.get();
    }

    public Movie checkMovieId(String titleId) {
        Optional<Movie> loaded = movieRepository.findById(titleId);
        if (loaded.isEmpty())
            throw new EntityNotFoundException(Movie.class.getName(), titleId);
        return loaded.get();
    }

}
