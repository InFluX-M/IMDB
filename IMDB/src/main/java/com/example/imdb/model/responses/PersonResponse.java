package com.example.imdb.model.responses;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class PersonResponse {

    String id;
    String name;
    Integer birthYear;
    Integer deathYear;
    String professions;
    String knownForTitles;
}
