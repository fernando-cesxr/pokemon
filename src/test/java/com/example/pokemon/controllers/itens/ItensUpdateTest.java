package com.example.pokemon.controllers.itens;


import com.example.pokemon.models.Itens;
import com.example.pokemon.repository.ItensRepository;
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
public class ItensUpdateTest {

    @MockBean
    private ItensRepository itensRepository;

    @Autowired
    private MockMvc mockMvc;

    private Itens itemPot;


    @BeforeEach
    public void setup(){
        itemPot = Itens.builder()
                .name("Potion")
                .description("Restores a small amount of HP.")
                .type("Health")
                .quantity("5")
                .build();
            }


    @Test
    public void test_Update_Success() throws Exception{

        Itens item2 = Itens.builder()
        .name("Super Potion")
        .description("Restores a moderate amount of HP.")
        .type("Health")
        .quantity("10")
        .build();


        when(itensRepository.findById(1L)).thenReturn(Optional.of(itemPot));
        when(itensRepository.save(itemPot)).thenReturn(itemPot);
        mockMvc.perform(put("/api/itens/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(item2))
        ).andExpect(jsonPath("$.name").value("Super Potion"));
    }


}
