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

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AttackUpdateTest {

    @MockBean
    private AttacksRepository attacksRepository;

    @Autowired
    private MockMvc mockMvc;

    private Attacks attackIceBeam;

    private Attacks attackFlamethrower;



    @BeforeEach
    public void setup() {
        attackIceBeam = Attacks.builder()
                .name("Ice Beam")
                .type("Ice")
                .damage(90)
                .isCharged(true)  // This is a charged move
                .build();

        attackFlamethrower = Attacks.builder()
                .name("Flamethrower")
                .type("Fire")
                .damage(95)
                .isCharged(false)  // This is a regular move, not charged
                .build();
    }


    @Test
    public void test_Update_Success() throws Exception{
        when(attacksRepository.findById(1L)).thenReturn(Optional.of(attackIceBeam));
        when(attacksRepository.save(attackIceBeam)).thenReturn(attackIceBeam);
        mockMvc.perform(put("/api/attacks/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(attackFlamethrower))
        ).andExpect(jsonPath("$.name").value("Flamethrower"));
    }

}
