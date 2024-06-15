package com.example.pokemon.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private String name;

    @NotNull
    private String type;

    @NotNull
    private String insignia;

    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL)
    private List<TrainersGym> trainersGyms;


}
