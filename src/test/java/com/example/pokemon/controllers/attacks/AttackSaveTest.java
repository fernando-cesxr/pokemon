package com.example.pokemon.controllers.attacks;

import com.example.pokemon.models.Attacks;
import com.example.pokemon.models.Pokemon;
import com.example.pokemon.repository.AttacksRepository;
import com.example.pokemon.repository.PokemonRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AttackSaveTest {

    @MockBean
    AttacksRepository attacksRepository;

    @Autowired
    MockMvc mockMvc;

    private Attacks attacks;

    @BeforeEach
    public void setup() {
        attacks = Attacks.builder()
                .name("Ice Beam")
                .type("Ice")
                .damage(90)
                .isCharged(true)  // This is a charged move
                .build();
    }

    @Test
    public void test_savePokemon() throws Exception {

        when(attacksRepository.save(attacks)).thenReturn(attacks);
        mockMvc.perform(post("/api/attacks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(attacks)))
                .andExpect(status().isCreated());
    }

}
