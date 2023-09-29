package com.attornatus.people.controller;

import com.attornatus.people.domain.CreateAddress;
import com.attornatus.people.entity.Address;
import com.attornatus.people.service.AddressService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Validated
public class AddressController {
    @Autowired
    public AddressService service;

    @PostMapping("/address/{personExternalId}")
    public Address save(@Valid @RequestBody CreateAddress request, @PathVariable("personExternalId") String personExternalId) {
        return service.persist(request.toEntity(), personExternalId);
    }

    @GetMapping("/adresses/person/{exteernalId}")
    public List<Address> findAllByPersonExternalId(
            @PathVariable("externalId") String externalId
    ) {
        return service.findAllByPersonId(externalId);
    }

    @PatchMapping("/addresses/{externalId}/person/{personExternalId}/main")
    public Address setMainAddress(
            @PathVariable("externalId") String externalId,
            @PathVariable("personExternalId") String personExternalId
    ) {
        return service.setMainAddress(personExternalId, externalId);
    }
}
