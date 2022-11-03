package com.example.imdb.repository;

import com.example.imdb.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
    Rating findByTitleId(String titleId);
    List<Rating> findByAverageVoteGreaterThan(double averageVote);
    List<Rating> findByAverageVoteLessThan(double averageVote);
    List<Rating> findByAverageVoteBetween(double min, double max);
    List<Rating> findByNumVotesGreaterThan(int numVotes);
    List<Rating> findByNumVotesLessThan(int numVotes);
    List<Rating> findByNumVotesBetween(int min, int max);
}
