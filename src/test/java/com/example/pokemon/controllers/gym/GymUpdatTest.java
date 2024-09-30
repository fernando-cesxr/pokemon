package com.example.pokemon.controllers.gym;

import com.example.pokemon.models.Gym;
import com.example.pokemon.repository.GymRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class GymUpdatTest {

    @MockBean
    private GymRepository gymRepository;

    @Autowired
    private MockMvc mockMvc;

    private Gym gymCampinho;

    @BeforeEach
    public void setup(){
        gymCampinho = Gym.builder()
                .name("Campinho de futebol vila augusta")
                .location("-7.1873, 116.0633")
                .insignia("Campinho de futebol vila augusta")
                .build();
    }

    @Test
    public void test_Update_Success() throws Exception{

        Gym gymBoneco = Gym.builder()
                .name("Boneco da praça")
                .location("3.5523, 73.9604")
                .insignia("Boneco da praça")
                .build();

        when(gymRepository.findById(1L)).thenReturn(Optional.of(gymCampinho));
        when(gymRepository.save(gymCampinho)).thenReturn(gymCampinho);
        mockMvc.perform(put("/api/gyms/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(gymBoneco))
        ).andExpect(jsonPath("$.name").value("Boneco da praça"));
    }

}
