package com.example.pokemon.controllers.trainers;

import com.example.pokemon.models.Trainers;
import com.example.pokemon.models.User;
import com.example.pokemon.repository.TrainersRepository;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TrainersGetByIdTest {

    @MockBean
    private TrainersRepository trainersRepository;

    @Autowired
    private MockMvc mockMvc;

    private Trainers trainers;

    @BeforeEach
    public void setup(){
        User user =  User.builder().email("Fernando@gmail.com").password("rM74%7^Ocnv%").build();
        trainers = Trainers.builder().insignias("Bolo de aniversário").level(32).name("koffee").user(user).build();
    }


    @Test
    public void test_GetById() throws Exception {
        when(trainersRepository.findById(1L)).thenReturn(Optional.of(trainers));
        mockMvc.perform(get("/api/trainers/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("koffee"));
    };

}