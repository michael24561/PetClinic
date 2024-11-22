package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.repositories.SpecialtyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    public SpecialtyServiceImpl(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public Specialty create(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    @Override
    public Specialty update(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    @Override
    public void delete(Integer id) {
        specialtyRepository.deleteById(id);
    }

    @Override
    public Specialty findById(Integer id) {
        Optional<Specialty> optionalSpecialty = specialtyRepository.findById(id);
        return optionalSpecialty.orElse(null);
    }

    @Override
    public List<Specialty> findAll() {
        return specialtyRepository.findAll();
    }
}
