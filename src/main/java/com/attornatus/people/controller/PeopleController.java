package com.attornatus.people.controller;


import com.attornatus.people.domain.CreatePerson;
import com.attornatus.people.entity.Person;
import com.attornatus.people.service.PersonService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Validated
public class PeopleController {
    @Autowired
    private PersonService service;

    @PostMapping("/people")
    public Person save(@Valid @RequestBody CreatePerson request) {
        return service.persist(request.toEntity());
    }

    @PutMapping("/people/{externalId}")
    public ResponseEntity<?> update(@Valid @RequestBody CreatePerson request, @PathVariable("externalId") String externalId) {
        try {
            return ResponseEntity.ok(service.update(request.toEntity(), externalId));
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @GetMapping("/people/{externalId}")
    public ResponseEntity<?> findByExternalId(@PathVariable("externalId") String externalId) {
        try {
            return ResponseEntity.ok(service.findByExternalId(externalId));
        } catch(Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @GetMapping("/people")
    public Page<Person> findPeople(
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "page", defaultValue =  "0") int page
    ) {
        PageRequest params = PageRequest.of(page, size);
        return service.findAll(params);
    }



}
