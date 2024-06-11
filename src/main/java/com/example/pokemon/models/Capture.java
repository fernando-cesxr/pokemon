package com.example.pokemon.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private LocalDate date_capture;

    @NotBlank
    private String capture_location;

    @EmbeddedId // identifying relation
    @ManyToOne
    @JoinColumn(name = "idPokemon")
    private Pokemon pokemon;

    @EmbeddedId
    @ManyToOne
    @JoinColumn(name = "idTrainers")
    private Trainers trainers;




}
