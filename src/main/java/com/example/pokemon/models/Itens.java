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
@Entity(name = "t_pk_itens")
@Builder
@NoArgsConstructor
public class Itens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String type;

    @NotBlank
    private String quantity;

    @OneToMany(mappedBy = "itens", cascade = CascadeType.ALL)
    private List<ItensTrainers> itensTrainers;

    @OneToMany(mappedBy = "itens", cascade = CascadeType.ALL)
    private List<ItensPokestops> itensPokestops;

}
