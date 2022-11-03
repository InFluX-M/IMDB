package com.example.imdb.repository;

import com.example.imdb.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
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
