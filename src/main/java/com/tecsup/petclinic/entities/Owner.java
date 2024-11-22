package com.tecsup.petclinic.entities;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entidad Owner
 */
@Entity(name = "owners")
@Data
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;

    private String address;

    private String city;

    private String telephone;

    public Owner() {}

    public Owner(String firstName, String lastName, String address, String city, String telephone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.telephone = telephone;
    }
}

