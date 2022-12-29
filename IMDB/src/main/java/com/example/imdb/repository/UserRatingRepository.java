package com.example.imdb.repository;

import com.example.imdb.model.UserRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRatingRepository extends JpaRepository<UserRating, Integer> {

}
