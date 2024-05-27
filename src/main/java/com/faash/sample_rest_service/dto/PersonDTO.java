package com.faash.sample_rest_service.dto;

import jakarta.validation.constraints.NotBlank;

public class PersonDTO {
    @NotBlank (message = "first name is mandatory")
    private String firstname;

    @NotBlank(message = "last name is mandatory")
    private String lastname;

    private String phoneNumber;

    private String email;

    public PersonDTO() {
    }

    public PersonDTO(String firstname, String lastname, String phoneNumber, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}
