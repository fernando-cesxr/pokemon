package com.example.pokemon.controllers.attacks;

import com.example.pokemon.models.Attacks;
import com.example.pokemon.repository.AttacksRepository;
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
public class AttackGetAllTest {

    @Mock
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
    public void test_GetAll() throws Exception {
        when(attacksRepository.findAll()).thenReturn(List.of(attack));
        mockMvc.perform(get("/api/attacks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
