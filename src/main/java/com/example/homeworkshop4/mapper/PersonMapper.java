package com.example.homeworkshop4.mapper;

import com.example.homeworkshop4.dto.PersonCreationDto;
import com.example.homeworkshop4.dto.PersonDto;
import com.example.homeworkshop4.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {
    public Person toPerson(PersonCreationDto personCreationDTO) {
        Person person = new Person();
        person.setName(personCreationDTO.getName());
        person.setSurname(personCreationDTO.getSurname());
        person.setEmail(personCreationDTO.getEmail());
        return person;
    }

    public PersonDto toPersonDTO(Person person) {
        return new PersonDto(person.getId(), person.getName(), person.getSurname(), person.getEmail(),
                person.getCarts());
    }
}
