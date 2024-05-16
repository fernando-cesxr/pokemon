package com.example.pokemon.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class Trainers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @NotBlank
    public String name;



    @NotBlank
    public String insignias;

    @NotBlank
    public int level;

    @OneToMany(mappedBy = "trainers", cascade = CascadeType.ALL)
    private List<Pokemon> pokemon;


    // relciamento com tabela cidade
//    @NotBlank
//    public String city;


}
