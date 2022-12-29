package com.example.imdb.model.responses;

import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Value
@Builder
public class DirectorResponse {
    String titleId;
    String title;
    Set<PersonResponse> directors;
}
