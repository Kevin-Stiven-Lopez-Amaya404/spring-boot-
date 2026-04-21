package com.sena.test.service;

import com.sena.test.dto.request.PersonRequestDto;
import com.sena.test.dto.response.PersonResponseDto;
import com.sena.test.entity.Person;
import com.sena.test.mapper.PersonMapper;
import com.sena.test.repository.IPersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    public final IPersonRepository repository;

    public PersonService(IPersonRepository repository) {
        this.repository = repository;
    }

    public PersonResponseDto create(PersonRequestDto dto) {
        Person entity = PersonMapper.toEntity(dto);
        return PersonMapper.toDto(repository.save(entity));
    }

    public List<PersonResponseDto> findAll() {
        return repository.findAll()
                .stream()
                .map(PersonMapper::toDto)
                .collect(Collectors.toList());
    }

    public PersonResponseDto findById(Long id) {
        return repository.findById(id)
                .map(PersonMapper::toDto)
                .orElse(null);
    }

    public Optional<Person> findEntityById(Long id) {
        return repository.findById(id);
    }

    public PersonResponseDto update(Long id, PersonRequestDto dto) {
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No existe una persona con id: " + id));
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        return PersonMapper.toDto(repository.save(entity));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
