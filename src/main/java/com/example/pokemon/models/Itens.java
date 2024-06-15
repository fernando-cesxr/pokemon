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
@Entity(name = "t_pk_itens")
@Builder
@NoArgsConstructor
public class Itens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String type;

    @NotNull
    private String quantity;


    @OneToMany(mappedBy = "itens", cascade = CascadeType.ALL)
    private List<ItensPokestops> itensPokestops;

}
