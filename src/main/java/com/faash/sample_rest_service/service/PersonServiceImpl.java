package com.faash.sample_rest_service.service;

import com.faash.sample_rest_service.exception.PersonNotFoundException;
import com.faash.sample_rest_service.model.Person;
import com.faash.sample_rest_service.repository.PersonRepository;
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
                        new PersonNotFoundException(String.format("Person With Id %d Doesn't Exist", id)));
    }

    @Override
    @Transactional
    public void deletePersonById(Integer id) {
        if (!personRepository.existsById(id)) {
            throw new PersonNotFoundException(String.format("Person With Id %d Doesn't Exist", id));
        }
        personRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updatePerson(Person person, Integer id) {
        Person existingPerson = personRepository.findById(id)
                .orElseThrow(() ->
                        new PersonNotFoundException(String.format("Person With Id %d Doesn't Exist", id)));
        existingPerson.setFirstname(person.getFirstname());
        existingPerson.setLastname(person.getLastname());
        existingPerson.setPhoneNumber(person.getPhoneNumber());
        existingPerson.setEmail(person.getEmail());
        personRepository.save(existingPerson);
    }
}
