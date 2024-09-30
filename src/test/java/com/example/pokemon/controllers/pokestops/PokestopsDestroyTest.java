package com.example.pokemon.controllers.pokestops;

import com.example.pokemon.models.Pokestops;
import com.example.pokemon.repository.PokestopsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
public class PokestopsDestroyTest {

    @MockBean
    private PokestopsRepository pokestopsRepository;

    @Autowired
    private MockMvc mockMvc;

    private Pokestops pokestops;


    @BeforeEach
    public void setup(){
        pokestops = Pokestops.builder()
                .name("Parque infantil")
                .description("Local de diversão para crianças")
                .location("-34.8628, 25.7879")
                .build();
    }

    @Test
    public void test_Destroy_Success() throws Exception {
        when(pokestopsRepository.findById(1L)).thenReturn(Optional.of(pokestops));
        when(pokestopsRepository.existsById(1L)).thenReturn(true);
        doNothing().when(pokestopsRepository).delete(pokestops);
        mockMvc.perform(delete("/api/pokestops/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

}
