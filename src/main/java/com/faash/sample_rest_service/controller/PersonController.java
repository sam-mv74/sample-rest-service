package com.faash.sample_rest_service.controller;

import com.faash.sample_rest_service.http.HttpHeader;
import com.faash.sample_rest_service.http.ResponseBodyModel;
import com.faash.sample_rest_service.dto.PersonDTO;
import com.faash.sample_rest_service.mapper.PersonMapper;
import com.faash.sample_rest_service.model.Person;
import com.faash.sample_rest_service.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
            description = "adds a person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person Added Successfully",
                    content = @Content(
                            schema = @Schema(implementation = ResponseBodyModel.class),
                            examples = @ExampleObject(value = """
                                        {
                                            "success": true,
                                            "result": "null,
                                            "message": "Person Added Successfully",
                                            "responseCode": "200",
                                            "trackingCode": "123e4567-e89b-12d3-a456-426614174000",
                                        }
                                    """)
                    )
            )
    })
    @PostMapping("/add-person")
    public ResponseEntity<ResponseBodyModel<Void>> addPerson(@RequestHeader(name = HttpHeader.SAMPLE) String header, @RequestBody @Valid PersonDTO personDTO) {
        Person person = PersonMapper.INSTANCE.convertToPerson(personDTO);
        personService.addPerson(person);
        ResponseBodyModel<Void> responseModel = new ResponseBodyModel<>();
        responseModel.setSuccess(true);
        responseModel.setMessage("Person Added Successfully");
        responseModel.setResponseCode("200");
        responseModel.setTrackingCode(UUID.randomUUID());
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @Operation(
            summary = "Retrieve a Person",
            description = "Retrieves a person by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person Retrieved Successfully",
                    content = @Content(
                            schema = @Schema(implementation = ResponseBodyModel.class),
                            examples = @ExampleObject(value = """
                                        {
                                            "success": true,
                                            "result": {
                                                "firstname": "ali",
                                                "lastname": "alipur",
                                                "phoneNumber": 09129843557,
                                                "email":"ali@gmail.com"
                                            },
                                            "message": "Person Retrieved Successfully",
                                            "responseCode": "200",
                                            "trackingCode": "123e4567-e89b-12d3-a456-426614174000",
                                        }
                                    """)
                    )
            )
    })
    @GetMapping("/get-person/{personId}")
    public ResponseEntity<ResponseBodyModel<PersonDTO>> getPersonById(@PathVariable Integer personId) {
        Person person = personService.getPersonById(personId);
        PersonDTO personDTO = PersonMapper.INSTANCE.convertToPersonDto(person);
        ResponseBodyModel <PersonDTO> responseModel = new ResponseBodyModel<>();
        responseModel.setSuccess(true);
        responseModel.setMessage("Person Retrieved Successfully");
        responseModel.setResponseCode("200");
        responseModel.setTrackingCode(UUID.randomUUID());
        responseModel.setResult(personDTO);
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete a Person",
            description = "deletes a person by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person Deleted Successfully",
                    content = @Content(
                            schema = @Schema(implementation = ResponseBodyModel.class),
                            examples = @ExampleObject(value = """
                                        {
                                            "success": true,
                                            "result": "null",
                                            "message": "Person Deleted Successfully",
                                            "responseCode": "200",
                                            "trackingCode": "123e4567-e89b-12d3-a456-426614174000",
                                        }
                                    """)
                    )
            )
    })
    @PostMapping("/delete-person/{personId}")
    public ResponseEntity<ResponseBodyModel<Void>> deletePerson(@PathVariable Integer personId) {
        personService.deletePersonById(personId);
        ResponseBodyModel<Void> responseModel = new ResponseBodyModel<>();
        responseModel.setSuccess(true);
        responseModel.setMessage("Person Deleted Successfully");
        responseModel.setResponseCode("200");
        responseModel.setTrackingCode(UUID.randomUUID());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            summary = "Update a Person",
            description = "updates a person by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person Updated Successfully",
                    content = @Content(
                            schema = @Schema(implementation = ResponseBodyModel.class),
                            examples = @ExampleObject(value = """
                                        {
                                            "success": true,
                                            "result": "null",
                                            "message": "Person Updated Successfully",
                                            "responseCode": "200",
                                            "trackingCode": "123e4567-e89b-12d3-a456-426614174000",
                                        }
                                    """)
                    )
            )
    })
    @PostMapping("/update-person/{personId}")
    public ResponseEntity<ResponseBodyModel<Void>> updatePerson(@PathVariable Integer personId, @RequestBody PersonDTO personDTO) {
        Person person = PersonMapper.INSTANCE.convertToPerson(personDTO);
        personService.updatePerson(person, personId);
        ResponseBodyModel <Void> responseModel = new ResponseBodyModel<>();
        responseModel.setSuccess(true);
        responseModel.setMessage("Person Updated Successfully");
        responseModel.setResponseCode("200");
        responseModel.setTrackingCode(UUID.randomUUID());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Not found",
                    content = @Content(
                            schema = @Schema(implementation = ResponseBodyModel.class),
                            examples = @ExampleObject(value = """
                                        {
                                            "success": false,
                                            "result": "null",
                                            "message": "Not Found",
                                            "responseCode": "400",
                                            "trackingCode": "123e4567-e89b-12d3-a456-426614174000",
                                        }
                                    """)
                    ))
    })
    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<ResponseBodyModel<Void>> handleNullPointerException(NullPointerException ex) {
        ResponseBodyModel<Void>responseModel = new ResponseBodyModel<>();
        responseModel.setSuccess(false);
        responseModel.setMessage(ex.getMessage());
        responseModel.setResponseCode("400");
        responseModel.setTrackingCode(UUID.randomUUID());
        return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
    }

    //test methods
//    @PostMapping("/throw-null-pointer-exception/{number}")
//    public ResponseEntity<Void> testNullPointerException(@PathVariable Integer number) {
//        if (number == 1) {
//            throw new NullPointerException();
//        }
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @PostMapping("/throw-exception/{number}")
//    public ResponseEntity<Void> testExceptionHandler(@PathVariable Integer number) {
//        if (number == 1) {
//            throw new PersonNotFoundException();
//        }
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
