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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
@AllArgsConstructor
public class FileService {

    private MovieRepository movieRepository;
    private PersonRepository personRepository;
    private RatingRepository ratingRepository;
    private SeriesEpisodeRepo seriesEpisodeRepo;
    private MovieService movieService;

    public List<MovieResponse> readMovies() throws IOException {

        String fileName = "src/main/resources/newMovie.tsv";
        String[] lines = Files.readAllLines(Path.of(fileName)).toArray(new String[0]);
        ArrayList<Movie> allMovies = new ArrayList<>();

        boolean firstLine = true;
        int i = 0;
        for (String s : lines) {

            if (firstLine) {
                firstLine = false;
                continue;
            }

            String[] parts = s.split("\t");

            for (int j = 0; j < parts.length; j++)
                if (Objects.equals(parts[j], "\\N")) parts[j] = null;

            Movie movie = Movie.builder()
                    .titleId(parts[0])
                    .type(TitleType.valueOf(parts[1].toUpperCase()))
                    .title(parts[2])
                    .isAdult(Boolean.parseBoolean(parts[4]))
                    .genres(parts[8])
                    .build();

            if (parts[5] != null) movie.setStartYear(Integer.valueOf(parts[5]));
            if (parts[6] != null) movie.setEndYear(Integer.valueOf(parts[6]));
            if (parts[7] != null) movie.setRuntimeMinutes(Integer.valueOf(parts[7]));

            allMovies.add(movie);
            i++;
        }

        return movieRepository.saveAll(allMovies).stream().map(Movie::response).toList();
    }

    public List<PersonResponse> readPersons() throws IOException {

        String fileName = "src/main/resources/newPerson.tsv";
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
                if (Objects.equals(parts[j], "\\N")) parts[j] = null;

            Random random = new Random();
            int birthDay = random.nextInt(28) + 1;
            int birthMonth = random.nextInt(12) + 1;
            int deathDay = random.nextInt(28) + 1;
            int deathMonth = random.nextInt(12) + 1;
            int birthYear;
            int deathYear;

            if (parts[2] == null) birthYear = random.nextInt(120) + 1900;
            else birthYear = Integer.parseInt(parts[2]);


            if (parts[3] == null) deathYear = random.nextInt(120) + 1900;
            else deathYear = Integer.parseInt(parts[3]);

            LocalDate birthDate = LocalDate.of(birthYear, birthMonth, birthDay);
            LocalDate deathDate = LocalDate.of(deathYear, deathMonth, deathDay);

            people.add(Person.builder()
                    .id(parts[0])
                    .name(parts[1])
                    .birthDate(birthDate)
                    .deathDate(deathDate)
                    .birthDateMonth(birthMonth)
                    .birthDateDay(birthDay)
                    .professions(parts[4])
                    .knownForTitles(parts[5])
                    .build());

            String[] titles = parts[5].split(",");

            people.get(i).setKnownForTitlesList(new ArrayList<>());
            try {

                for (String st : titles) {

                    System.out.println(st);
                    if (movieRepository.findById(st).isPresent()) {
                        people.get(i).getKnownForTitlesList().add(st);
                        Movie movie = movieRepository.findById(st).get();
                        movie.getActors().add(people.get(i));
                        movieRepository.save(movie);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            i++;
        }

        return personRepository.saveAll(people).stream().map(Person::response).toList();
    }

    public List<RatingResponse> readRatings() throws IOException {

        String fileName = "src/main/resources/newRating.tsv";
        String[] lines = Files.readAllLines(Path.of(fileName)).toArray(new String[0]);
        ArrayList<Rating> ratings = new ArrayList<>();

        boolean firstLine = true;

        for (String s : lines) {

            if (firstLine) {
                firstLine = false;
                continue;
            }

            String[] parts = s.split("\t");

            for (int j = 0; j < parts.length; j++)
                if (Objects.equals(parts[j], "\\N")) parts[j] = null;

            Movie movie = movieRepository.findById(parts[0]).get();

            ratings.add(Rating.builder()
                    .movie(movie)
                    .avgRating(Float.parseFloat(parts[1]))
                    .numVotes(Integer.parseInt(parts[2]))
                    .build());
        }

        return ratingRepository.saveAll(ratings).stream().map(Rating::response).toList();

    }

    public List<SeriesEpisodeResponse> readEpisodes() throws IOException {

        String fileName = "src/main/resources/newEpisode.tsv";
        String[] lines = Files.readAllLines(Path.of(fileName)).toArray(new String[0]);
        ArrayList<SeriesEpisode> episodes = new ArrayList<>();

        boolean firstLine = true;

        for (String s : lines) {

            if (firstLine) {
                firstLine = false;
                continue;
            }

            String[] parts = s.split("\t");

            for (int j = 0; j < parts.length; j++)
                if (Objects.equals(parts[j], "\\N")) parts[j] = null;

            Movie episode = movieRepository.findById(parts[0]).get();
            episodes.add(SeriesEpisode.builder()
                    .episode(episode)
                    .parent(Movie.builder().titleId(parts[1]).build())
                    .seasonNumber(Integer.parseInt(parts[2]))
                    .episodeNumber(Integer.parseInt(parts[3]))
                    .build());
        }

        return seriesEpisodeRepo.saveAll(episodes).stream().map(SeriesEpisode::response).toList();
    }

    public List<DirectorResponse> readCrews() throws IOException {

        String fileName = "src/main/resources/newCrew.tsv";
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
                if (Objects.equals(parts[j], "\\N")) parts[j] = null;

            String[] directors = parts[1].split(",");

            Movie movie = movieRepository.findById(parts[0]).get();
            for (String d : directors) {
                movie.getDirectors().add(personRepository.findById(d).get());
            }

            movies.add(movie);
            movieRepository.save(movie);
        }

        return movies.stream().map(Movie::directorResponse).toList();
    }

}
