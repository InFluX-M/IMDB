package com.example.imdb.model.responses;

import com.example.imdb.model.Comment;
import com.example.imdb.model.Person;
import com.example.imdb.model.TitleType;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.Set;

@Value
@Builder
public class DirectorResponse {
    String titleId;
    String title;
    Set<Person> directors;
}
