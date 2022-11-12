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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    private Movie movie;

    private Float avgRating;
    private Integer numVotes;

    public RatingRequest request() {
        return RatingRequest.builder()
                .titleId(movie.getTitleId())
                .avgRating(avgRating)
                .numVotes(numVotes)
                .build();
    }

    public RatingResponse response() {
        return RatingResponse.builder()
                .avgRating(avgRating)
                .numVotes(numVotes)
                .build();
    }

}
