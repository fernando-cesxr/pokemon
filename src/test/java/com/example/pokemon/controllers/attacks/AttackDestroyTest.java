package com.example.pokemon.controllers.attacks;

import com.example.pokemon.models.Attacks;
import com.example.pokemon.repository.AttacksRepository;
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

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AttackDestroyTest {

    @MockBean
    private AttacksRepository attacksRepository;

    @Autowired
    private MockMvc mockMvc;

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
    public void test_Destroy_Success() throws Exception {
        when(attacksRepository.findById(1L)).thenReturn(Optional.of(attacks));
        when(attacksRepository.existsById(1L)).thenReturn(true);
        doNothing().when(attacksRepository).delete(attacks);
        mockMvc.perform(delete("/api/attacks/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }


}
