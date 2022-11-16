package com.example.imdb.service;

import com.example.imdb.exception.EntityNotFoundException;
import com.example.imdb.model.Person;
import com.example.imdb.model.requests.PersonRequest;
import com.example.imdb.model.responses.PersonResponse;
import com.example.imdb.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonService {

    private PersonRepository personRepository;

    public PersonResponse addPerson(PersonRequest request) {

        Person person = Person.builder()
                .id(request.id())
                .name(request.name())
                .birthDate(request.birthYear())
                .deathDate(request.deathYear())
                .professions(request.professions())
                .knownForTitles(request.knownForTitles())
                .birthDateDay(request.birthYear().getDayOfMonth())
                .birthDateMonth(request.birthYear().getMonthValue())
                .knownForTitlesList(Arrays.stream(request.knownForTitles().split(",")).toList())
                .professionsList(Arrays.stream(request.professions().split(",")).toList())
                .build();

        return personRepository.save(person).response();
    }

    public PersonResponse updatePerson(String personId, PersonRequest request) {
        Person person = checkPersonId(personId);

        if (request.deathYear() != null) person.setDeathDate(request.deathYear());
        if (request.birthYear() != null) person.setBirthDate(request.birthYear());
        if (request.knownForTitles() != null) person.setKnownForTitles(request.knownForTitles());
        if (request.name() != null) person.setName(request.name());
        if (request.professions() != null) person.setProfessions(request.professions());

        return personRepository.save(person).response();
    }

    public void deletePerson(String personId) {
        personRepository.deleteById(personId);
    }

    public List<PersonResponse> getAllPersons() {
        return personRepository.findAll().stream().map(Person::response).toList();
    }

    public PersonResponse getPerson(String personId) {
        return checkPersonId(personId).response();
    }

    public List<PersonResponse> getPeopleByBirthDate(Integer month, Integer day) {
        return personRepository.findByBirthDateMonthAndBirthDateDay(month, day).stream().map(Person::response).toList();
    }

    public Person checkPersonId(String personId) {
        Optional<Person> loaded = personRepository.findById(personId);
        if (loaded.isEmpty())
            throw new EntityNotFoundException(Person.class.getName(), personId);
        return loaded.get();
    }
}
