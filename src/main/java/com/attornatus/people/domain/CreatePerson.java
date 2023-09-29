package com.attornatus.people.domain;


import com.attornatus.people.entity.Person;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CreatePerson {
    @JsonProperty("nome")
    private String name;
    @JsonProperty("dataNascimento")
    private LocalDate birthDate;

    public Person toEntity() {
        return Person.builder()
                .birthDate(birthDate)
                .name(name)
                .build();
    }
}
