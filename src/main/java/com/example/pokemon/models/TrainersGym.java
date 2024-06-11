package com.example.pokemon.models;

import jakarta.persistence.*;
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
    private Long position;

    @EmbeddedId
    @ManyToOne
    @JoinColumn(name = "idTrainers")
    private Trainers trainers;

    @EmbeddedId
    @ManyToOne
    @JoinColumn(name = "idGym")
    private Gym gym;
}
