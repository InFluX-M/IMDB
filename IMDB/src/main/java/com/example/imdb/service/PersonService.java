package com.example.imdb.service;

import com.example.imdb.exception.EntityNotFoundException;
import com.example.imdb.model.Person;
import com.example.imdb.model.requests.PersonRequest;
import com.example.imdb.model.responses.PersonResponse;
import com.example.imdb.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonService {

    private PersonRepository personRepository;

    public PersonResponse addPerson(PersonRequest request) {

        Person person = Person.builder()
                .id(request.getId())
                .name(request.getName())
                .birthDate(request.getBirthYear())
                .deathDate(request.getDeathYear())
                .professions(request.getProfessions())
                .knownForTitles(request.getKnownForTitles())
                .build();
        return personRepository.save(person).response();
    }

    public PersonResponse updatePerson(String personId, PersonRequest request)
    {
        Person person = checkPersonId(personId);

        if(request.getDeathYear() != null) person.setDeathDate(request.getDeathYear());
        if(request.getBirthYear() != null) person.setBirthDate(request.getBirthYear());
        if(request.getKnownForTitles() != null) person.setKnownForTitles(request.getKnownForTitles());
        if(request.getName() != null) person.setName(request.getName());
        if(request.getProfessions() != null) person.setProfessions(request.getProfessions());

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

    public List<PersonResponse> getPersonsByBirthDate(int year, int month) {
        return personRepository.findByBirthDateMonthAndBirthDateDay(year, month).stream().map(Person::response).toList();
    }
    public Person checkPersonId(String personId) {
        Optional<Person> loaded = personRepository.findById(personId);
        if (loaded.isEmpty())
            throw new EntityNotFoundException(Person.class.getName(), personId);
        return loaded.get();
    }
}
