package com.example.pokemon.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Entity(name = "t_pk_gym")
@Builder
@NoArgsConstructor
public class Gym {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String type;

    @NotBlank
    private String insignia;

    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL)
    private List<TrainersGym> trainersGyms;


}
