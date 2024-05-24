package com.faash.sample_rest_service.service;

import com.faash.sample_rest_service.model.Person;

public interface PersonService {
    void addPerson(Person person);
    Person getPersonById(Integer id);
}
