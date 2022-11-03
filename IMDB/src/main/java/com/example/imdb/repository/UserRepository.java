package com.example.imdb.repository;

import com.example.imdb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
    List<User> findByUsernameContaining(String username);
    List<User> findByWatchlistContaining(String titleId);
    List<User> findByFavoriteListContaining(String titleId);
}
