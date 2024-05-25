package com.faash.sample_rest_service.service;

import com.faash.sample_rest_service.model.Person;
import com.faash.sample_rest_service.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    @Transactional
    public void addPerson(Person person) {
        personRepository.save(person);
    }

    @Override
    public Person getPersonById(Integer id) {
        return personRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(String.format("Person With Id %d Not Found", id)));
    }
}
