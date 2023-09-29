package com.attornatus.people.service.impl;

import com.attornatus.people.entity.Person;
import com.attornatus.people.repository.PersonRepository;
import com.attornatus.people.service.PersonService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository repository;

    @Transactional
    public Person persist(Person person){
        return repository.save(person);
    }

    @Transactional
    public Person update(Person person, String externalId) {
        var persistedPerson = repository.findByExternalId(externalId)
                .orElseThrow(() -> new RuntimeException("Person doesn't exist with externalId: " + externalId));

        persistedPerson.setName(person.getName());
        persistedPerson.setBirthDate(person.getBirthDate());

        return repository.save(person);
    }

    public Person findByExternalId(String externalId) {
        return repository.findByExternalId(externalId)
                .orElseThrow(() -> new RuntimeException("Person doesn't exist with externalId: " + externalId));
    }

    public Page<Person> findAll(PageRequest page) {
        return repository.findAll(page);
    }
}
