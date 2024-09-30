package com.example.pokemon.controllers.gym;

import com.example.pokemon.models.Gym;
import com.example.pokemon.repository.GymRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class GymGetByIdTest {

    @MockBean
    private GymRepository gymRepository;

    @Autowired
    private MockMvc mockMvc;

    private Gym gym;

    @BeforeEach
    public void setup(){
        gym = Gym.builder()
                .name("Campinho de futebol vila augusta")
                .location("-7.1873, 116.0633")
                .insignia("Campinho de futebol vila augusta")
                .build();
    }

    @Test
    public void test_GetById() throws Exception {
        when(gymRepository.findById(1L)).thenReturn(Optional.of(gym));
        mockMvc.perform(get("/api/gyms/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Campinho de futebol vila augusta"));
    };

}
