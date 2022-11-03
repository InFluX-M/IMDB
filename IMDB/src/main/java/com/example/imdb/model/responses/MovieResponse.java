package com.example.imdb.model.responses;

import com.example.imdb.model.Comment;
import com.example.imdb.model.Person;
import com.example.imdb.model.TitleType;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.Set;

@Builder
@Value
public class MovieResponse {

    String titleId;
    TitleType type;
    String title;
    boolean isAdult;
    int startYear;
    int endYear;
    int runtimeMinutes;
    String genres;
    Set<Person> directors;
    List<Comment> comments;
    List<EpisodeResponse> episodeResponses;
}
