package com.example.pokemon.controllers.pokemon;

import com.example.pokemon.models.Pokemon;
import com.example.pokemon.repository.PokemonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PokemonGetAllTest {

    @Mock
    PokemonRepository pokemonRepository;

    @Autowired
    MockMvc mockMvc;

    private Pokemon pokemonGarchomp;

    @BeforeEach
    public void setup() {
        pokemonGarchomp = Pokemon.builder()
                .id(1L)
                .name("Garchomp")
                .type("Dragon/Ground")
                .height(1.90)
                .firstAttack("Earthquake")
                .secondAttack("Dragon Claw")
                .nr_Attack(15)
                .nr_Defense(12)
                .nr_Hp(200)
                .level(42)
                .build();
    };

    @Test
    public void test_GetAllPokemon() throws Exception {
        when(pokemonRepository.findAll()).thenReturn(List.of(pokemonGarchomp));
        mockMvc.perform(get("/api/attacks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }



}
