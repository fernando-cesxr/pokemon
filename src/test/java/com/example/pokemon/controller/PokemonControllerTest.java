package com.example.pokemon.controller;

import com.example.pokemon.models.Pokemon;
import com.example.pokemon.repository.PokemonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PokemonControllerTest {

    @Mock
    PokemonRepository pokemonRepository;

    @Autowired
    MockMvc mockMvc;

    private Pokemon pokemonGarchomp;

    @BeforeEach
    public void setup(){
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
    public void test_savePokemon() throws Exception {

        when(pokemonRepository.save(pokemonGarchomp)).thenReturn(pokemonGarchomp);
        mockMvc.perform(post("/api/pokemons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(pokemonGarchomp)))
                .andExpect(status().isCreated());
    }

    @Test
    public void test_GetByIdPokemon() throws Exception {
        when(pokemonRepository.findById(1L)).thenReturn(Optional.of(pokemonGarchomp));
        mockMvc.perform(get("/api/pokemons/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Garchomp"));
    }

    @Test
    public void test_GetAllPokemon() throws Exception {
        when(pokemonRepository.findAll()).thenReturn(List.of(pokemonGarchomp));
        mockMvc.perform(get("/api/pokemons")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void test_Destroy_Success() throws Exception {
        doNothing().when(pokemonRepository).delete(pokemonGarchomp);
        mockMvc.perform(delete("/api/pokemons/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(pokemonGarchomp))).andExpect(status().isNoContent());
    }

}

