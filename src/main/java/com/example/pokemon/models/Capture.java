package com.example.pokemon.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity(name = "t_pk_capture")
@Builder
@NoArgsConstructor
public class Capture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate date_capture;

    @NotNull
    private String capture_location;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idPokemon")
    private Pokemon pokemon;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idTrainers")
    private Trainers trainers;




}
