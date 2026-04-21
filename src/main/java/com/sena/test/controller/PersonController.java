package com.sena.test.controller;

import com.sena.test.dto.request.PersonRequestDto;
import com.sena.test.dto.response.PersonResponseDto;
import com.sena.test.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @PostMapping
    public PersonResponseDto create(@Valid @RequestBody PersonRequestDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<PersonResponseDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public PersonResponseDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public PersonResponseDto update(@PathVariable Long id, @Valid @RequestBody PersonRequestDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
