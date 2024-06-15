package com.example.pokemon.controller;


import com.example.pokemon.exceptions.RestNotFoundException;
import com.example.pokemon.models.PokemonAttacks;
import com.example.pokemon.repository.PokemonAttacksRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.web.PagedResourcesAssembler;


@RestController
@RequestMapping("/api/pokemonAttacks")
public class PokemonAttacksController {

    @Autowired
    PokemonAttacksRepository pokemonAttacksRepository;

    @Autowired
    PagedResourcesAssembler assembler;

    @GetMapping
    public PagedModel<PokemonAttacks> index(@RequestParam(required = false) String search, @PageableDefault(size = 10) Pageable pageagle){
        Page<PokemonAttacks> pokemonAttacks = pokemonAttacksRepository.findAll(pageagle);

        return assembler.toModel(pokemonAttacks.map(PokemonAttacks::toEntityModel));
    }

    @GetMapping("{id}")
    public EntityModel<PokemonAttacks> show(@PathVariable Long id){
        var pokemonAttacks = pokemonAttacksRepository.findById(id).orElseThrow(()-> new RestNotFoundException("pokemonAttack not nound"));

        return pokemonAttacks.toEntityModel();
    }

    @PostMapping
    public ResponseEntity<PokemonAttacks> create(@RequestBody @Valid PokemonAttacks pokemonAttacks){
        pokemonAttacksRepository.save(pokemonAttacks);
        return ResponseEntity.status(HttpStatus.CREATED).body(pokemonAttacks);
    }

    @PutMapping("{id}")
    public EntityModel<PokemonAttacks> update(@PathVariable Long id, @RequestBody @Valid PokemonAttacks pokemonAttacks){
        pokemonAttacksRepository.findById(id).orElseThrow(()-> new RestNotFoundException("pokemonAttack not found"));

        pokemonAttacks.setId(id);
        pokemonAttacksRepository.save(pokemonAttacks);
        return pokemonAttacks.toEntityModel();
    }


    @DeleteMapping("{id}")
    public ResponseEntity<PokemonAttacks> destroy(@PathVariable Long id){
        var pokemonAttacks = pokemonAttacksRepository.findById(id).orElseThrow(()-> new RestNotFoundException("pokemonAttack not found"));
        pokemonAttacksRepository.delete(pokemonAttacks);
        return ResponseEntity.noContent().build();
    }


}
