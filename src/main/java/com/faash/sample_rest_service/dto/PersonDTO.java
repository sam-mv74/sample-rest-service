package com.faash.sample_rest_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonDTO {
    private String firstname;

    private String lastname;

    private String phoneNumber;

    private String email;
}
