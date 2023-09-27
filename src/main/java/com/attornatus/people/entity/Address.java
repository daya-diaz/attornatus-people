package com.attornatus.people.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "TB_ADDRESS")
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "NAM_STREET")
    private String street;
    @Column(name = "IDT_EXTERNAL_ID")
    private String externalId;
    @Column(name = "IDT_ZIPCODE")
    private String zipcode;
    @Column(name = "IDT_NUMBER")
    private String number;
    @Column(name = "IDT_CITY")
    private String city;
    @Builder.Default
    @Column(name = "FLG_MAIN")
    private Boolean main = Boolean.FALSE;
    @ManyToOne
    private Person person;

    @PrePersist
    public void setup() {
        this.externalId = UUID.randomUUID().toString();
    }

}
