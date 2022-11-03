package com.example.imdb.repository;

import com.example.imdb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    List<User> findByUsernameContaining(String username);

//    List<User> findByWatchlistContaining(String titleId);

//    List<User> findByFavoriteListContaining(String titleId);
}
