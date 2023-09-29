package com.attornatus.people.repository;


import com.attornatus.people.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("SELECT a FROM Address a WHERE a.person.externalId = :externalId")
    List<Address> findAllByPersonExternalId(@Param("externalId") String externalId);

    @Query("SELECT a FROM Address a WHERE a.main = true and a.person.externalId = :externalId")
    Optional<Address> findMainByPersonExternalId(@Param("externalId") String externalId);

    @Query("SELECT a FROM Address a WHERE a.externalId = :externalId and a.person.externalId = :personExternalId")
    Optional<Address> findByExternalIdAndPersonExternalId(@Param("externalId") String externalId, @Param("personExternalId") String personExternalId);
}
