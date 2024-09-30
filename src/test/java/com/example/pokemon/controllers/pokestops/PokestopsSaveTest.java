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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PokestopsSaveTest {

    @MockBean
    private PokestopsRepository pokestopsRepository;

    @Autowired
    private MockMvc mockMvc;

    private Pokestops pokestops;


    @BeforeEach
    public void setup(){
        pokestops = Pokestops.builder()
                .name("Parque infantil")
                .description("Local de diversão para crianças")
                .location("-34.8628, 25.7879")
                .build();
    }


    @Test
    public void test_save() throws Exception {

        when(pokestopsRepository.save(pokestops)).thenReturn(pokestops);
        mockMvc.perform(post("/api/pokestops")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(pokestops)))
                .andExpect(status().isCreated());
    }


}
