package com.example.pokemon.controllers.trainers;


import com.example.pokemon.models.Trainers;
import com.example.pokemon.models.User;
import com.example.pokemon.repository.TrainersRepository;
import com.example.pokemon.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TrainersSaveTest {

    @MockBean
    private TrainersRepository trainersRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    private Trainers trainers;

    private User user;

    @BeforeEach
    public void setup() {
        user = User.builder()
                .id(1L)
                .email("Fernando@gmail.com")
                .password("rM74%7^Ocnv%")
                .build();

        trainers = Trainers.builder()
                .insignias("Bolo de aniversário")
                .level(32)
                .name("koffee")
                .user(user)
                .build();
    }

    @Test
    public void test_save() throws Exception {

        when(userRepository.save(user)).thenReturn(user);
        when(trainersRepository.save(trainers)).thenReturn(trainers);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        mockMvc.perform(post("/api/trainers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(trainers)))
                .andExpect(status().isCreated());
    }

}
