package com.example.imdb.model.requests;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class PersonRequest {

    String id;
    String name;
    int birthYear;
    int deathYear;
    String professions;
    String knownForTitles;
}
