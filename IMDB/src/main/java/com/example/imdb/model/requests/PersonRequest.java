package com.example.imdb.model.requests;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.Date;

@Builder
@Value
public class PersonRequest {

    String id;
    String name;
    LocalDate birthYear;
    LocalDate deathYear;
    String professions;
    String knownForTitles;
}
