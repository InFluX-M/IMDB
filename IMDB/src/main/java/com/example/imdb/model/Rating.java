package com.example.imdb.model;

import com.example.imdb.model.requests.RatingRequest;
import com.example.imdb.model.responses.RatingResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

    @Id
    @GeneratedValue
    private int id;
    private String titleId;
    private float avgRating;
    private int numVotes;

    public RatingRequest request() {
        return RatingRequest.builder()
                .titleId(titleId)
                .avgRating(avgRating)
                .numVotes(numVotes)
                .build();
    }

    public RatingResponse response() {
        return RatingResponse.builder()
                .titleId(titleId)
                .avgRating(avgRating)
                .numVotes(numVotes)
                .build();
    }
}
