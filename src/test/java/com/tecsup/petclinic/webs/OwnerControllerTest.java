package com.tecsup.petclinic.webs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class OwnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Owner sampleOwner;

    @BeforeEach
    void setUp() {
        // No insertamos nuevos datos, solo aseguramos que hay datos existentes
        Optional<Owner> existingOwner = ownerRepository.findById(1L);  // Suponiendo que el ID 1 es un propietario existente
        sampleOwner = existingOwner.orElseThrow(() -> new RuntimeException("No owner found for testing"));
    }

    @Test
    void testFindAllOwners() throws Exception {
        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(10)))  // Verificamos que hay 10 propietarios en la base de datos
                .andExpect(jsonPath("$[0].firstName", is("afasf")));  // Verificamos que el primer propietario tiene el nombre "afasf"
    }

    @Test
    void testFindOwnerById() throws Exception {
        mockMvc.perform(get("/owners/{id}", sampleOwner.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(sampleOwner.getFirstName())));  // Verificamos que el nombre del propietario es igual al de la base de datos
    }

    @Test
    void testUpdateOwner() throws Exception {
        sampleOwner.setAddress("789 Oak St");

        mockMvc.perform(put("/owners/{id}", sampleOwner.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleOwner)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address", is("789 Oak St")));  // Verificamos que la dirección se ha actualizado correctamente
    }
    }

   /* @Test
    void testDeleteOwner() throws Exception {
        mockMvc.perform(delete("/owners/{id}", sampleOwner.getId()))
                .andExpect(status().isOk());

        Optional<Owner> deletedOwner = ownerRepository.findById(sampleOwner.getId());
        assert (deletedOwner.isEmpty());  // Verificamos que el propietario ha sido eliminado de la base de datos
    }
}*/
