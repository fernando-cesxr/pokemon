package com.example.pokemon.models;

import com.example.pokemon.controller.ItensController;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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


    @OneToMany(mappedBy = "itens", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<ItensPokestops> itensPokestops;


    public EntityModel<Itens> toEntityModel(){
        return EntityModel.of(
                this,
                linkTo(methodOn(ItensController.class).show(id)).withSelfRel(),
                linkTo(methodOn(ItensController.class).show(id)).withRel("destroy")
        );
    }

}
