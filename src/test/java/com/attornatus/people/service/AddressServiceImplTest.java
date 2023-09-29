package com.attornatus.people.service;

import com.attornatus.people.entity.Address;
import com.attornatus.people.entity.Person;
import com.attornatus.people.repository.AddressRepository;
import com.attornatus.people.service.impl.AddressServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


import java.time.LocalDate;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class AddressServiceImplTest {
    @InjectMocks
    private AddressServiceImpl addressService;

    @Mock
    private AddressRepository repository;

    @Mock
    private PersonService personService;

    @Test
    void shouldPersistEntityWhenItHaveTheFields() {
        Person person = getPerson();
        Address request = getAddress();

        when(repository.save(any())).thenReturn(request);
        when(personService.findByExternalId(any())).thenReturn(person);
        var persistedAddress = addressService.persist(request, "");

        assertEquals(persistedAddress.getPerson().getName(), person.getName());
    }

    @Test
    void shouldCallRepositoryWhenLookForPersonId() {
        addressService.findAllByPersonId("");
        verify(repository, times(1)).findAllByPersonExternalId(any());
    }

    @Test
    void shouldSaveMainAddressWhenPreviousDoesntExist() {
        when(repository.findMainByPersonExternalId(any())).thenReturn(Optional.empty());
        when(repository.findByExternalIdAndPersonExternalId(any(), any()))
                .thenReturn(Optional.of(mock(Address.class)));
        addressService.setMainAddress("", "");
        verify(repository, times(1)).save(any());
    }

    @Test
    void shouldSaveMainAddresAndDisablePreviousOneWhenPreviousDoesntExist() {
        when(repository.findMainByPersonExternalId(any())).thenReturn(Optional.of(mock(Address.class)));
        when(repository.findByExternalIdAndPersonExternalId(any(), any()))
                .thenReturn(Optional.of(mock(Address.class)));
        addressService.setMainAddress("", "");
        verify(repository, times(2)).save(any());
    }

    @Test
    void shouldThrownExceptionWhenAddressNotFound() {
        when(repository.findMainByPersonExternalId(any())).thenReturn(Optional.of(mock(Address.class)));
        when(repository.findByExternalIdAndPersonExternalId(any(), any()))
                .thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> addressService.setMainAddress("", ""));
    }

    protected Person getPerson() {
        return Person.builder().name("JOSE").birthDate(LocalDate.now()).build();
    }
    protected Address getAddress() {
        return Address.builder()
                .city("RECIFE")
                .number("734")
                .zipcode("52071255")
                .street("Av Dr Eurico Chaves")
                .build();
    }

}
