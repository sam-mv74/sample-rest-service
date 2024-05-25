package com.faash.sample_rest_service.exception;

public class PersonNotFoundException extends RuntimeException{
    public PersonNotFoundException() {
    }

    public PersonNotFoundException(String message) {
        super(message);
    }
}
