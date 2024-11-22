package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.services.VetService;
import com.tecsup.petclinic.exception.PetNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/vets")
public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @GetMapping
    public ResponseEntity<List<Vet>> findAll() {
        return ResponseEntity.ok(vetService.findAll());
    }

    @PostMapping
    public ResponseEntity<Vet> create(@RequestBody Vet vet) {
        return ResponseEntity.ok(vetService.create(vet));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vet> findById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(vetService.findById(id));
        } catch (PetNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vet> update(@PathVariable Integer id, @RequestBody Vet vet) {
        try {
            Vet existingVet = vetService.findById(id);
            existingVet.setName(vet.getName());
            existingVet.setSpecialization(vet.getSpecialization());
            return ResponseEntity.ok(vetService.update(existingVet));
        } catch (PetNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            vetService.delete(id);
            return ResponseEntity.ok("Vet deleted successfully!");
        } catch (PetNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
