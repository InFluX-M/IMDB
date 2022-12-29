package com.example.imdb.model;

import com.example.imdb.model.responses.RatingResponse;
import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Rating {

    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    private Movie movie;

    private Float avgRating;
    private Integer numVotes;

    public RatingResponse response() {
        return RatingResponse.builder()
                .avgRating(avgRating)
                .numVotes(numVotes)
                .build();
    }
}
