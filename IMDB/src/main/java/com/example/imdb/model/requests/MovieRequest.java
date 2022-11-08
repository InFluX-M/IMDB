package com.example.imdb.model.requests;

import com.example.imdb.model.Person;
import com.example.imdb.model.TitleType;
import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Builder
@Value
public class MovieRequest {

    String titleId;
    TitleType type;
    String title;
    Boolean isAdult;
    Integer startYear;
    Integer endYear;
    Integer runtimeMinutes;
    String genres;
    Set<Person> directors;
}
