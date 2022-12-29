package com.example.imdb.repository;

import com.example.imdb.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
    List<Person> findByBirthDateMonthAndBirthDateDay(int month, int day);
}
