package com.attornatus.people.domain;

import com.attornatus.people.entity.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CreateAddress {
    @JsonProperty("rua")
    private String street;
    @JsonProperty("cep")
    private String zipcode;
    @JsonProperty("numero")
    private String number;
    @JsonProperty("cidade")
    private String city;

    public Address toEntity() {
        return Address.builder()
                .street(street)
                .zipcode(zipcode)
                .number(number)
                .city(city)
                .build();
    }
}
