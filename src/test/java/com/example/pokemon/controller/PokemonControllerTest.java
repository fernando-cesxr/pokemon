package com.example.pokemon.controller;

import com.example.pokemon.config.DatabaseSeeder;
import com.example.pokemon.models.Pokemon;
import com.example.pokemon.repository.PokemonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PokemonControllerTest {

    @Mock
    PokemonRepository pokemonRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void test_savePokemon() throws Exception {
                Pokemon pokemonGarchomp = Pokemon.builder()
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
                when(pokemonRepository.save(pokemonGarchomp)).thenReturn(pokemonGarchomp);
                mockMvc.perform(post("/api/pokemons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(pokemonGarchomp)))
                        .andExpect(status().isCreated());
    }
}

