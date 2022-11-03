package com.example.imdb.repository;

import com.example.imdb.model.FavoriteList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteListRepository extends JpaRepository<FavoriteList, Integer> {
    FavoriteList findById(int id);
}
