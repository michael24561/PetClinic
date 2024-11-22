package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exception.PetNotFoundException;

import java.util.List;

public interface VetService {

    Vet create(Vet vet);

    Vet update(Vet vet);

    void delete(Integer id) throws PetNotFoundException;

    Vet findById(Integer id) throws PetNotFoundException;

    List<Vet> findByName(String name);

    List<Vet> findAll();
}