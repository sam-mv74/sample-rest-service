package com.faash.sample_rest_service.controller;

import com.faash.sample_rest_service.dto.PersonDTO;
import com.faash.sample_rest_service.mapper.PersonMapper;
import com.faash.sample_rest_service.model.Person;
import com.faash.sample_rest_service.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person-server")
@Tag(name = "Person Server", description = "person server APIs")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @Operation(
            summary = "Add a Person",
            description = "adds a person by required fields")
    @PostMapping("/add-person")
    public ResponseEntity<Void> addPerson(@RequestHeader(name = "sample") String header, @RequestBody PersonDTO personDTO) {
        Person person = PersonMapper.INSTANCE.convertToPerson(personDTO);
        personService.addPerson(person);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(
            summary = "Retrieve a Person",
            description = "retrieves a person by id")
    @GetMapping("/get-person/{personId}")
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable Integer personId) {
        Person person = personService.getPersonById(personId);
        PersonDTO personDTO = PersonMapper.INSTANCE.convertToPersonDto(person);
        return new ResponseEntity<>(personDTO, HttpStatus.OK);
    }
}
