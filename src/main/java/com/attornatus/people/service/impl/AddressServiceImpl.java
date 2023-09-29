package com.attornatus.people.service.impl;

import com.attornatus.people.entity.Address;
import com.attornatus.people.entity.Person;
import com.attornatus.people.repository.AddressRepository;
import com.attornatus.people.service.AddressService;
import com.attornatus.people.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository repository;

    @Autowired
    private PersonService personService;

    public Address persist(Address address, String externalId) {
        Person person = personService.findByExternalId(externalId);
        address.setPerson(person);
        return repository.save(address);
    };

    @Override
    public List<Address> findAllByPersonId(String personExternalId) {
        return repository.findAllByPersonExternalId(personExternalId);
    }

    public Address setMainAddress(String personExternalId, String addressExternalId) {
        Optional<Address> mainAddressOpt = repository.findMainByPersonExternalId(personExternalId);

        if(mainAddressOpt.isPresent()){
            var mainAddress = mainAddressOpt.get();
            mainAddress.setMain(Boolean.FALSE);
            repository.save(mainAddress);
        }

        Optional<Address> addressOpt = repository.findByExternalIdAndPersonExternalId(addressExternalId, personExternalId);

        if(addressOpt.isEmpty()) {
            throw new RuntimeException("Address doesn't exist with externalId: " + addressExternalId + "to personExternalId: " + personExternalId);
        }

        var address = addressOpt.get();

        address.setMain(Boolean.TRUE);
        return repository.save(address);
    }
}
