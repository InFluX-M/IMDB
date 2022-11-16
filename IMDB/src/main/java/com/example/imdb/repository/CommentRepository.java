package com.example.imdb.repository;

import com.example.imdb.model.Comment;
import com.example.imdb.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByMovie(Movie movie);
}
