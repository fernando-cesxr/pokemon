package com.example.pokemon.controllers.user;

import com.example.pokemon.models.User;
import com.example.pokemon.repository.UserRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserUpdateTest {


    @MockBean
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    private User user;

    @BeforeEach
    public void setup() {
        user = User.builder()
                .email("example@gmail.com")
                .password("rM74%7^Ocnv%")
                .build();
    }


    @Test
    public void test_UpdateUser_Success() throws Exception{

        String email = "cesar@gmail.com";
        String password = "rM74%7^Ocnv%";

        String jsonRequestBody2 = String.format(
                "{\"email\":\"%s\",\"password\":\"%s\"}", email, password
        );

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        mockMvc.perform(put("/api/users/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBody2))
                .andExpect(status().isOk());
    }

}
