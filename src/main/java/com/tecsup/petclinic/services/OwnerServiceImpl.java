package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.repositories.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Owner create(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public Owner update(Owner owner) throws OwnerNotFoundException {
        if (!ownerRepository.existsById(owner.getId())) {
            throw new OwnerNotFoundException("Owner not found with ID: " + owner.getId());
        }
        return ownerRepository.save(owner);
    }

    @Override
    public void delete(Integer id) throws OwnerNotFoundException {
        Owner owner = findById(id);
        ownerRepository.delete(owner);
    }

    @Override
    public Owner findById(Integer id) throws OwnerNotFoundException {
        Optional<Owner> owner = ownerRepository.findById(id);
        if (owner.isEmpty()) {
            throw new OwnerNotFoundException("Owner not found with ID: " + id);
        }
        return owner.get();
    }

    @Override
    public List<Owner> findByFirstName(String firstName) {
        return ownerRepository.findByFirstName(firstName);
    }

    @Override
    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }
}
