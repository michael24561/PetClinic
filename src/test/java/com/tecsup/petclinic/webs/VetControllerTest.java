package com.tecsup.petclinic.webs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.repositories.VetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class VetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private VetRepository vetRepository;

    @Test
    void testCreateVet() throws Exception {
        Vet vet = new Vet("Dr. John", "Surgery");

        mockMvc.perform(post("/vets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vet)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Dr. John"));
    }

    @Test
    void testFindAllVets() throws Exception {
        mockMvc.perform(get("/vets"))
                .andExpect(status().isOk());
    }

    @Test
    void testFindVetById() throws Exception {
        Vet vet = vetRepository.save(new Vet("Dr. Jane", "Dentistry"));

        mockMvc.perform(get("/vets/" + vet.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Dr. Jane"));
    }

    @Test
    void testDeleteVet() throws Exception {
        Vet vet = vetRepository.save(new Vet("Dr. Max", "Orthopedics"));

        mockMvc.perform(delete("/vets/" + vet.getId()))
                .andExpect(status().isOk());
    }
}