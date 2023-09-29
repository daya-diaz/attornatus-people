package com.attornatus.people.service;

import com.attornatus.people.entity.Address;

import java.util.List;

public interface AddressService {
    Address persist(Address address, String externalId);
    List<Address> findAllByPersonId(String personExternalId);
    Address setMainAddress(String personExternalId, String addressExternalId);
}
