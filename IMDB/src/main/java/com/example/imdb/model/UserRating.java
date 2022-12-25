package com.example.imdb.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserRating {
    @Id
    @GeneratedValue
    private Integer id;

    private String userId;
    private String titleId;
    private Integer rating;
}
