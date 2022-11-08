package com.example.imdb.service;

import com.example.imdb.exception.EntityNotFoundException;
import com.example.imdb.exception.InvalidRatingException;
import com.example.imdb.exception.InvalidUsernameException;
import com.example.imdb.model.FavoriteList;
import com.example.imdb.model.Movie;
import com.example.imdb.model.Rating;
import com.example.imdb.model.User;
import com.example.imdb.model.requests.FavoriteListRequest;
import com.example.imdb.model.requests.UserRequest;
import com.example.imdb.model.responses.FavoriteListResponse;
import com.example.imdb.model.responses.MovieResponse;
import com.example.imdb.model.responses.RatingResponse;
import com.example.imdb.model.responses.UserResponse;
import com.example.imdb.repository.FavoriteListRepository;
import com.example.imdb.repository.MovieRepository;
import com.example.imdb.repository.RatingRepository;
import com.example.imdb.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private RatingRepository ratingRepository;
    private FavoriteListRepository favoriteListRepository;
    private MovieRepository movieRepository;

    public UserResponse addUser(UserRequest request) {
        // todo password validation?
        try {
            User user = User.builder()
                    .username(request.getUsername())
                    .password(request.getPassword())
                    .build();
            return userRepository.save(user).response();
        } catch (RuntimeException e) {
            throw new InvalidUsernameException(request.getUsername());
        }
    }

    public void updateUser(String username, UserRequest request) {
        // todo valid pass?
        try {
            User user = checkUsername(username);
            if (request.getUsername() != null) user.setUsername(request.getUsername());
            if (request.getPassword() != null) user.setPassword(request.getPassword());
        } catch (RuntimeException e) {
            throw new InvalidUsernameException(username);
        }
    }

    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(User::response).toList();
    }

    public UserResponse getUserById(String username) {
        return checkUsername(username).response();
    }

    public List<FavoriteListResponse> getFavLists(String username) {
        return userRepository.findById(username).get().getFavoriteLists().stream().map(FavoriteList::response).toList();
    }

    public Set<MovieResponse> getWatchLists(String username) {
        return userRepository.findById(username).get().getWatchList().stream().map(Movie::response).collect(Collectors.toSet());
    }

    public FavoriteListResponse addFavList(FavoriteListRequest request) {
        FavoriteList list = FavoriteList.builder()
                .size(0)
                .name(request.getName())
                .build();

        return favoriteListRepository.save(list).response();
    }

    public FavoriteListResponse addToFavList(String listName, String titleId) {

        Movie movie = checkMovieId(titleId);
        FavoriteList list = favoriteListRepository.getByName(listName);

        list.getMovies().add(movie);

        return favoriteListRepository.save(list).response();
    }

    public List<MovieResponse> addToWatchList(String username, String titleId) {

        User user = checkUsername(username);
        Movie movie = checkMovieId(titleId);

        user.getWatchList().add(movie);
        userRepository.save(user);

        return user.getWatchList().stream().map(Movie::response).toList();
    }

    public RatingResponse rateMovie(String titleId, int rating) {

        if (rating < 1 || rating > 10)
            throw new InvalidRatingException();

        Rating ratingObj = ratingRepository.findByTitleId(titleId);

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
