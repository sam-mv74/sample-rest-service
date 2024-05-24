package com.faash.sample_rest_service.mapper;

import com.faash.sample_rest_service.dto.PersonDTO;
import com.faash.sample_rest_service.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper (componentModel = "spring")
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    Person convertToPerson(PersonDTO personDTO);

    PersonDTO convertToPersonDto(Person person);
}
