package com.example.imdb.file;

import com.example.imdb.model.Movie;
import com.example.imdb.model.Person;
import com.example.imdb.model.TitleType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;

public class TSVFile {

    public static Movie[] readMovies(String fileName) throws IOException {

        String[] lines = Files.readAllLines(Path.of(fileName)).toArray(new String[0]);
        Movie[] allMovies = new Movie[lines.length - 1];

        boolean firstLine = true;
        int i = 0;

        for (String s : lines) {

            if (firstLine) {
                firstLine = false;
                continue;
            }

            String[] parts = s.split("\t");

            for (int j = 0; j < parts.length; j++)
                if (Objects.equals(parts[j], "\\N")) parts[j] = "0";

            allMovies[i++] = Movie.builder()
                    .titleId(parts[0])
                    .type(TitleType.valueOf(parts[1].toUpperCase()))
                    .title(parts[2])
                    .isAdult(Boolean.parseBoolean(parts[4]))
                    .startYear(Integer.parseInt(parts[5]))
                    .endYear(Integer.parseInt(parts[6]))
                    .runtimeMinutes(Integer.parseInt(parts[7]))
                    .genres(parts[8])
                    .build();
        }

        return allMovies;
    }

    public static Person[] readPersons(String fileName) throws IOException {

        String[] lines = Files.readAllLines(Path.of(fileName)).toArray(new String[0]);
        Person[] people = new Person[lines.length - 1];

        boolean firstLine = true;
        int i = 0;

        for (String s : lines) {

            if (firstLine) {
                firstLine = false;
                continue;
            }

            String[] parts = s.split("\t");
            System.err.println(Arrays.toString(parts));

            for (int j = 0; j < parts.length; j++)
                if (Objects.equals(parts[j], "\\N")) parts[j] = "0";

            people[i++] = Person.builder()
                    .id(parts[0])
                    .name(parts[1])
                    .birthYear(Integer.parseInt(parts[2]))
                    .deathYear(Integer.parseInt(parts[3]))
                    .professions(parts[4])
                    .knownForTitles(parts[5])
                    .build();
        }

        return people;
    }
}
