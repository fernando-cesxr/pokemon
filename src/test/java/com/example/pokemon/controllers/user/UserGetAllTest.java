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

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserGetAllTest {

    @Mock
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    private User user;


    @BeforeEach
    public void setup() {
        user = User.builder()
                .email("example@example.com")
                .password("SecureP@ssw0rd!")
                .build();
    }

    @Test
    public void test_GetAllPokemon() throws Exception {
        when(userRepository.findAll()).thenReturn(List.of(user));
        mockMvc.perform(get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
