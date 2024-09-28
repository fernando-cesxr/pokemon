package com.example.pokemon.controllers.pokemon;

import com.example.pokemon.models.Pokemon;
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

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PokemonUpdateTest {

    @MockBean
    private PokemonRepository pokemonRepository;

    @Autowired
    private MockMvc mockMvc;

    private Pokemon pokemonGarchomp;

    private Pokemon pokemonGroudon;

    @BeforeEach
    public void setup() {
        pokemonGarchomp = Pokemon.builder()
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


        pokemonGroudon = Pokemon.builder()
                .name("Groudon")
                .type("Fire/Ground")
                .height(1.90)
                .firstAttack("aa")
                .secondAttack("jj")
                .nr_Attack(15)
                .nr_Defense(12)
                .nr_Hp(200)
                .level(42)
                .build();
    }

    @Test
    public void test_Update_Success() throws Exception{
        when(pokemonRepository.findById(1L)).thenReturn(Optional.of(pokemonGarchomp));
        when(pokemonRepository.save(pokemonGarchomp)).thenReturn(pokemonGarchomp);
        mockMvc.perform(put("/api/pokemons/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(pokemonGroudon))
        ).andExpect(jsonPath("$.name").value("Groudon"));
    }



}
