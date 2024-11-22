package com.tecsup.petclinic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tecsup.petclinic.entities.Vet;

import java.util.List;

@Repository
public interface VetRepository extends JpaRepository<Vet, Integer> {

    List<Vet> findByName(String name);
}
