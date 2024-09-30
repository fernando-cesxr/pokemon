package com.example.pokemon.controllers.itens;

import com.example.pokemon.models.Itens;
import com.example.pokemon.repository.ItensRepository;
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
public class ItensDestroyTest {

    @MockBean
    private ItensRepository itensRepository;

    @Autowired
    private MockMvc mockMvc;

    private Itens itens;

    @BeforeEach
    public void setup(){
        itens = Itens.builder()
                .name("Potion")
                .description("Restores a small amount of HP.")
                .type("Health")
                .quantity("5")
                .build();

    }


    @Test
    public void test_Destroy_Success() throws Exception {

        when(itensRepository.findById(1L)).thenReturn(Optional.of(itens));
        when(itensRepository.existsById(1L)).thenReturn(true);
        doNothing().when(itensRepository).delete(itens);
        mockMvc.perform(delete("/api/itens/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

}
