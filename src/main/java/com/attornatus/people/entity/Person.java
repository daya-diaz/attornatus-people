package com.attornatus.people.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_PEOPLE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Person {
    @Id
    @Column(name = "IDT_PERSON")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "IDT_EXTERNAL_ID", unique = true, updatable = false)
    private String externalId;
    @Column(name = "NAM_NAME")
    private String name;
    @Column(name = "IDT_BIRTHDATE")
    private LocalDate birthDate;
    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Address> addresses;

    @PrePersist
    public void setup() {
        this.externalId = UUID.randomUUID().toString();
    }
}
