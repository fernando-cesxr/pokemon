package com.example.pokemon.models;

import com.example.pokemon.controllers.ItensPokestopsController;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Data
@AllArgsConstructor
@Entity(name = "t_pk_itensPokestops")
@Builder
@NoArgsConstructor
public class ItensPokestops {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idItens")
    private Itens itens;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idPokestops")
    private Pokestops pokestops;

    public EntityModel<ItensPokestops> toEntityModel(){
        return EntityModel.of(
                this,
                linkTo(methodOn(ItensPokestopsController.class).show(id)).withSelfRel(),
                linkTo(methodOn(ItensPokestopsController.class).show(id)).withRel("destroy")
        );
    }


}
