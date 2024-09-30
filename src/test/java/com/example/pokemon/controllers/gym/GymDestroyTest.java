package com.example.pokemon.controllers.gym;

import com.example.pokemon.models.Gym;
import com.example.pokemon.repository.GymRepository;
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
public class GymDestroyTest {

    @MockBean
    private GymRepository gymRepository;

    @Autowired
    private MockMvc mockMvc;

    private Gym gym;

    @BeforeEach
    public void setup(){
        gym = Gym.builder()
                .name("Campinho de futebol vila augusta")
                .location("-7.1873, 116.0633")
                .insignia("Campinho de futebol vila augusta")
                .build();
    }

    @Test
    public void test_Destroy_Success() throws Exception {
        when(gymRepository.findById(1L)).thenReturn(Optional.of(gym));
        when(gymRepository.existsById(1L)).thenReturn(true);
        doNothing().when(gymRepository).delete(gym);
        mockMvc.perform(delete("/api/gyms/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }


}
