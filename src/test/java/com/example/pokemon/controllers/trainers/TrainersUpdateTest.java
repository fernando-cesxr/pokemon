package com.example.pokemon.controllers.trainers;

import com.example.pokemon.models.Trainers;
import com.example.pokemon.models.User;
import com.example.pokemon.repository.TrainersRepository;
import com.example.pokemon.repository.UserRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TrainersUpdateTest {


    @MockBean
    private TrainersRepository trainersRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    private Trainers trainers1, trainers2;

    private User user1;

    @BeforeEach
    public void setup() {
        user1 = User.builder()
                .id(1L)
                .email("Fernando@gmail.com")
                .password("rM74%7^Ocnv%")
                .build();

        trainers1 = Trainers.builder()
                .id(1L)
                .insignias("Bolo de aniversário")
                .level(32)
                .name("koffee")
                .user(user1)
                .build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(trainersRepository.findById(1L)).thenReturn(Optional.of(trainers1));
        when(trainersRepository.save(trainers1)).thenReturn(trainers1);
    }

    @Test
    public void test_update() throws Exception {

        Trainers trainers2 = Trainers.builder()
                .id(1L)
                .insignias("Nova Insignia")
                .level(50)
                .name("Charizard")
                .user(user1)
                .build();

        mockMvc.perform(put("/api/trainers/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(trainers2)))
                .andExpect(status().isOk());
    }

}
