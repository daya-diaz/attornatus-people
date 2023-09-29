package com.attornatus.people.service;

import com.attornatus.people.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface PersonService {
    Person persist(Person person);
    Person update(Person person, String externalId);
    Person findByExternalId(String externalId);
    Page<Person> findAll(PageRequest page);
}
