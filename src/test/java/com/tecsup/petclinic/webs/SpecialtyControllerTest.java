package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SpecialtyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Test
    void testGetAllSpecialties() throws Exception {
        mockMvc.perform(get("/specialties"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0))));
    }

    @Test
    void testCreateSpecialty() throws Exception {
        String specialtyJson = "{\"name\":\"Dentist\"}";

        mockMvc.perform(post("/specialties")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(specialtyJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Dentist"));
    }

    @Test
    void testGetSpecialtyById() throws Exception {
        Specialty specialty = specialtyRepository.save(new Specialty("Surgeon"));

        mockMvc.perform(get("/specialties/" + specialty.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Surgeon"));
    }

    @Test
    void testUpdateSpecialty() throws Exception {
        Specialty specialty = specialtyRepository.save(new Specialty("Nurse"));
        String updatedJson = "{\"name\":\"Veterinarian\"}";

        mockMvc.perform(put("/specialties/" + specialty.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Veterinarian"));
    }

    @Test
    void testDeleteSpecialty() throws Exception {
        Specialty specialty = specialtyRepository.save(new Specialty("Therapist"));

        mockMvc.perform(delete("/specialties/" + specialty.getId()))
                .andExpect(status().isNoContent());
    }
}
