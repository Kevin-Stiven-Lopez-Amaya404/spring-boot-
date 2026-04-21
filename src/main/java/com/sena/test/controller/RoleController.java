package com.sena.test.controller;

import com.sena.test.dto.request.RoleRequestDto;
import com.sena.test.dto.response.RoleResponseDto;
import com.sena.test.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @PostMapping
    public RoleResponseDto create(@Valid @RequestBody RoleRequestDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<RoleResponseDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public RoleResponseDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/name/{name}")
    public RoleResponseDto findByName(@PathVariable String name) {
        return service.findByName(name);
    }

    @PutMapping("/{id}")
    public RoleResponseDto update(@PathVariable Long id, @Valid @RequestBody RoleRequestDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
