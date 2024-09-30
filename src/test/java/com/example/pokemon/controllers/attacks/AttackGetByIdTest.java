package com.example.pokemon.controllers.attacks;

import com.example.pokemon.models.Attacks;
import com.example.pokemon.models.Pokemon;
import com.example.pokemon.repository.AttacksRepository;
import com.example.pokemon.repository.PokemonRepository;
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
public class AttackGetByIdTest {

    @MockBean
    private AttacksRepository attacksRepository;

    @Autowired
    private MockMvc mockMvc;

    private Attacks attack;

    @BeforeEach
    public void setup() {
        attack = Attacks.builder()
                .name("Ice Beam")
                .type("Ice")
                .damage(90)
                .isCharged(true)  // This is a charged move
                .build();
    }

    @Test
    public void test_GetById() throws Exception {
        when(attacksRepository.findById(1L)).thenReturn(Optional.of(attack));
        mockMvc.perform(get("/api/attacks/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ice Beam"));
    };

}
