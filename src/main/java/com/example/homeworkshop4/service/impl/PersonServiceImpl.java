package com.example.homeworkshop4.service.impl;

import com.example.homeworkshop4.dto.PersonCreationDto;
import com.example.homeworkshop4.dto.PersonDto;
import com.example.homeworkshop4.exception.NotFoundException;
import com.example.homeworkshop4.mapper.PersonMapper;
import com.example.homeworkshop4.model.Person;
import com.example.homeworkshop4.repository.PersonRepository;
import com.example.homeworkshop4.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.homeworkshop4.utils.ErrorMessages.PERSON_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper mapper;

    @Override
    public PersonDto createPerson(PersonCreationDto personCreationDTO) {
        Person newPerson = mapper.toPerson(personCreationDTO);
        personRepository.save(newPerson);
        return mapper.toPersonDTO(newPerson);
    }

    @Override
    public PersonDto getPersonById(Integer id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(PERSON_NOT_FOUND, id)));
        return mapper.toPersonDTO(person);
    }

    @Override
    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    @Override
    public PersonDto updatePersonById(Integer id, PersonCreationDto personCreationDTO) {
        Person personForUpdate = personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(PERSON_NOT_FOUND, id)));
        personForUpdate.setName(personCreationDTO.getName());
        personForUpdate.setSurname(personCreationDTO.getSurname());
        personForUpdate.setEmail(personForUpdate.getEmail());
        personRepository.save(personForUpdate);
        return mapper.toPersonDTO(personForUpdate);
    }

    @Override
    public void deletePersonById(Integer id) {
        Person personForDelete = personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(PERSON_NOT_FOUND, id)));
        personRepository.delete(personForDelete);
    }
}
