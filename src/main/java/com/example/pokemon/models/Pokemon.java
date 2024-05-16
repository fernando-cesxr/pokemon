package com.example.pokemon.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank
    public String name;

    @NotBlank
    public String type;

    @NotBlank
    public double height;

    @NotBlank
    public String movements;

    @NotBlank
    public int nr_Attack;

    @NotBlank
    public int nr_Defense;

    @NotBlank
    public int nr_Hp;

    @NotBlank
    public int level;

    @ManyToOne
    @JoinColumn(name = "idTrainer")
    private Trainers trainers;




}
