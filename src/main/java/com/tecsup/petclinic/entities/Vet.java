package com.tecsup.petclinic.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "vets")
@Data
public class Vet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String specialization;

    public Vet() {
    }

    public Vet(String name, String specialization) {
        this.name = name;
        this.specialization = specialization;
    }
}