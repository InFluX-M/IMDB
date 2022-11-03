package com.example.imdb.repository;

import com.example.imdb.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
    Rating findByTitleId(String titleId);

    List<Rating> findByAvgRatingGreaterThan(double averageVote);

    List<Rating> findByAvgRatingLessThan(double averageVote);

    List<Rating> findByAvgRatingBetween(double min, double max);

    List<Rating> findByNumVotesGreaterThan(int numVotes);

    List<Rating> findByNumVotesLessThan(int numVotes);

    List<Rating> findByNumVotesBetween(int min, int max);
}
