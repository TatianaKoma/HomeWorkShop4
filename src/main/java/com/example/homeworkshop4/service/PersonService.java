package com.example.homeworkshop4.service;

import com.example.homeworkshop4.dto.PersonCreationDto;
import com.example.homeworkshop4.dto.PersonDto;
import com.example.homeworkshop4.model.Person;

import java.util.List;

public interface PersonService {

    PersonDto createPerson(PersonCreationDto personCreationDTO);

    PersonDto getPersonById(Integer id);

    List<Person> getPersons();

    PersonDto updatePersonById(Integer id, PersonCreationDto personCreationDTO);

    void deletePersonById(Integer id);
}
