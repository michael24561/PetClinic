package com.tecsup.petclinic.repositories;

import com.tecsup.petclinic.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {

    // Buscar propietarios por nombre
    List<Owner> findByFirstName(String firstName);

    // Buscar propietarios por ciudad
    List<Owner> findByCity(String city);

    Optional<Owner> findById(long l);
}
