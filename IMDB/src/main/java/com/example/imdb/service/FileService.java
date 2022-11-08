package com.example.imdb.service;

import com.example.imdb.model.*;
import com.example.imdb.model.responses.*;
import com.example.imdb.repository.MovieRepository;
import com.example.imdb.repository.PersonRepository;
import com.example.imdb.repository.RatingRepository;
import com.example.imdb.repository.SeriesEpisodeRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.spec.ECPoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class FileService {

    private MovieRepository movieRepository;

    private PersonRepository personRepository;

    private RatingRepository ratingRepository;

    private SeriesEpisodeRepo seriesEpisodeRepo;

    public List<MovieResponse> readMovies() throws IOException {

        String fileName = "src/main/resources/Movie.tsv";
        String[] lines = Files.readAllLines(Path.of(fileName)).toArray(new String[0]);
        ArrayList<Movie> allMovies = new ArrayList<>();

        boolean firstLine = true;

        for (String s : lines) {

            if (firstLine) {
                firstLine = false;
                continue;
            }

            String[] parts = s.split("\t");

            for (int j = 0; j < parts.length; j++)
                if (Objects.equals(parts[j], "\\N")) parts[j] = "0";

            allMovies.add(Movie.builder()
                    .titleId(parts[0])
                    .type(TitleType.valueOf(parts[1].toUpperCase()))
                    .title(parts[2])
                    .isAdult(Boolean.parseBoolean(parts[4]))
                    .startYear(Integer.parseInt(parts[5]))
                    .endYear(Integer.parseInt(parts[6]))
                    .runtimeMinutes(Integer.parseInt(parts[7]))
                    .genres(parts[8])
                    .build());
        }

        return movieRepository.saveAll(allMovies).stream().map(Movie::response).toList();
    }

    public List<PersonResponse> readPersons() throws IOException {

        String fileName = "src/main/resources/Person.tsv";
        String[] lines = Files.readAllLines(Path.of(fileName)).toArray(new String[0]);
        ArrayList<Person> people = new ArrayList<>();

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

            people.add(Person.builder()
                    .id(parts[0])
                    .name(parts[1])
                    .birthYear(Integer.parseInt(parts[2]))
                    .deathYear(Integer.parseInt(parts[3]))
                    .professions(parts[4])
                    .knownForTitles(parts[5])
                    .build());

            String[] titles = parts[5].split("\\,");

            for (String st : titles) {
                people.get(i-1).getKnownForTitlesList().add(st);
                Movie movie = movieRepository.findById(st).get();
                movie.getActors().add(people.get(i-1));
                movieRepository.save(movie);
            }
        }

        return personRepository.saveAll(people).stream().map(Person::response).toList();
    }

    public List<RatingResponse> readRatings() throws IOException {

        String fileName = "src/main/resources/Rating.tsv";
        String[] lines = Files.readAllLines(Path.of(fileName)).toArray(new String[0]);
        ArrayList<Rating> ratings = new ArrayList<>();

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

            ratings.add(Rating.builder()
                    .titleId(parts[0])
                    .avgRating(Float.parseFloat(parts[1]))
                    .numVotes(Integer.parseInt(parts[2]))
                    .build());
        }

        return ratingRepository.saveAll(ratings).stream().map(Rating::response).toList();

    }

    public List<SeriesEpisodeResponse> readEpisodes() throws IOException {

        String fileName = "src/main/resources/Episode.tsv";
        String[] lines = Files.readAllLines(Path.of(fileName)).toArray(new String[0]);
        ArrayList<SeriesEpisode> episodes = new ArrayList<>();

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

            episodes.add(SeriesEpisode.builder()
                    .titleId(parts[0])
                    .parent(Movie.builder().titleId(parts[1]).build())
                    .seasonNumber(Integer.parseInt(parts[2]))
                    .episodeNumber(Integer.parseInt(parts[3]))
                    .build());
        }

        return seriesEpisodeRepo.saveAll(episodes).stream().map(SeriesEpisode::response).toList();
    }

    public List<DirectorResponse> readCrews() throws IOException {

        String fileName = "src/main/resources/Crew.tsv";
        String[] lines = Files.readAllLines(Path.of(fileName)).toArray(new String[0]);
        ArrayList<Movie> movies = new ArrayList<>();

        boolean firstLine = true;

        for (String s : lines) {

            if (firstLine) {
                firstLine = false;
                continue;
            }

            String[] parts = s.split("\t");

            for (int j = 0; j < parts.length; j++)
                if (Objects.equals(parts[j], "\\N")) parts[j] = "0";

            String[] directors = parts[1].split(",");

            Movie movie = movieRepository.findById(parts[0]).get();
            for(String d : directors)
            {
                movie.getDirectors().add(personRepository.findById(d).get());
            }

            movies.add(movie);
            movieRepository.save(movie);
        }

        return movies.stream().map(Movie::directorResponse).toList();
    }

}
