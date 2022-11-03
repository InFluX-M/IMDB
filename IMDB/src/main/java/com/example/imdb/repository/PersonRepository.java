package com.example.imdb.repository;

import com.example.imdb.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, String> {

    Person findByID(String id);
    List<Person> findByNameContaining(String firstName);
    List<Person> findByBirthYear(int birthYear);
    List<Person> findByBirthYearBetween(int birthYear1, int birthYear2);
    List<Person> findByBirthYearGreaterThan(int birthYear);
    List<Person> findByBirthYearLessThan(int birthYear);
    List<Person> findByDeathYear(int deathYear);
    List<Person> findByDeathYearBetween(int deathYear1, int deathYear2);
    List<Person> findByDeathYearGreaterThan(int deathYear);
    List<Person> findByDeathYearLessThan(int deathYear);


}
