package com.example.imdb.repository;

import com.example.imdb.model.FavoriteList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteListRepository extends JpaRepository<FavoriteList, Integer> {
    FavoriteList getByName(String name);
}
