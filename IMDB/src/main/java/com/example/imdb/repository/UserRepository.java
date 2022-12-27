package com.example.imdb.repository;

import com.example.imdb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByUsernameContaining(String username);

//    List<User> findByWatchlistContaining(String titleId);

//    List<User> findByFavoriteListContaining(String titleId);

    boolean existsByUsername(String username);

    User findByUsername(String username);

    //    Optional<User> findByUsernameOptional(String username);
    @Transactional
    void deleteByUsername(String username);
}
