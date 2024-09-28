package com.example.pokemon.controllers.pokemon;

import com.example.pokemon.models.Pokemon;
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
public class PokemonGetByIdTest {

    @MockBean
    private PokemonRepository pokemonRepository;

    @Autowired
    private MockMvc mockMvc;

    private Pokemon pokemonGarchomp;

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
    }


    @Test
    public void test_GetByIdPokemon() throws Exception {
        when(pokemonRepository.findById(1L)).thenReturn(Optional.of(pokemonGarchomp));
        mockMvc.perform(get("/api/pokemons/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Garchomp"));
    };

}
