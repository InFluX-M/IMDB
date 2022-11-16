package com.example.imdb.model.requests;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PersonRequest(String id, String name, LocalDate birthYear, LocalDate deathYear, String professions, String knownForTitles) { }