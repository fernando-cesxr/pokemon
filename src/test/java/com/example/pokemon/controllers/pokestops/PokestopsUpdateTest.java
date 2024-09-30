package com.example.pokemon.controllers.pokestops;

import com.example.pokemon.models.Pokestops;
import com.example.pokemon.repository.PokestopsRepository;
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
public class PokestopsUpdateTest {

    @MockBean
    private PokestopsRepository pokestopsRepository;

    @Autowired
    private MockMvc mockMvc;

    private Pokestops pokestopParque;


    @BeforeEach
    public void setup(){
        pokestopParque = Pokestops.builder()
                .name("Parque infantil")
                .description("Local de diversão para crianças")
                .location("-34.8628, 25.7879")
                .build();
    }

    @Test
    public void test_Update_Success() throws Exception{

        Pokestops pokestopGrafite = Pokestops.builder()
                .name("Grafite Camaro SS")
                .location("-40.7838, -135.9595")
                .build();

        when(pokestopsRepository.findById(1L)).thenReturn(Optional.of(pokestopParque));
        when(pokestopsRepository.save(pokestopParque)).thenReturn(pokestopParque);
        mockMvc.perform(put("/api/pokestops/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(pokestopGrafite))
            ).andExpect(jsonPath("$.name").value("Grafite Camaro SS"));
        }
}
