package com.example.pokemon.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.IdentityHashMap;

@Data
@NoArgsConstructor
@Entity(name = "t_pk_trainersGym")
@Builder
@AllArgsConstructor
public class TrainersGym {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idTrainers")
    private Trainers trainers;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idGym")
    private Gym gym;
}
