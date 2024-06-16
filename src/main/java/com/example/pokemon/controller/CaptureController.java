package com.example.pokemon.controller;

import com.example.pokemon.exceptions.RestNotFoundException;
import com.example.pokemon.models.Capture;
import com.example.pokemon.models.Pokemon;
import com.example.pokemon.models.Trainers;
import com.example.pokemon.repository.CaptureRepository;
import com.example.pokemon.repository.PokemonRepository;
import com.example.pokemon.repository.TrainersRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/captures")
public class CaptureController {

    @Autowired
    CaptureRepository captureRepository;

    @Autowired
    PokemonRepository pokemonRepository;

    @Autowired
    TrainersRepository trainersRepository;

    @Autowired
    PagedResourcesAssembler assembler;

    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String search, @PageableDefault(size = 10)Pageable pageable){
        Page<Capture> capture  = captureRepository.findAll(pageable);
        return assembler.toModel(capture.map(Capture::toEntityModel));

    }

    @GetMapping("{id}")
    public EntityModel<Capture> show(@PathVariable Long id){
        var capture = captureRepository.findById(id).orElseThrow(()-> new RestNotFoundException("capture not found"));

        return capture.toEntityModel();
    }

    @PostMapping
    public ResponseEntity<Capture> create(@RequestBody @Valid Capture capture){
        Pokemon pokemon = pokemonRepository.findById(capture.getPokemon().getId()).orElseThrow(()-> new RestNotFoundException("Pokemon not found"));
        Trainers trainers = trainersRepository.findById(capture.getTrainers().getId()).orElseThrow(()-> new RestNotFoundException("Trainer not found"));


        capture.setTrainers(trainers);
        capture.setPokemon(pokemon);

        captureRepository.save(capture);
        return ResponseEntity.status(HttpStatus.CREATED).body(capture);
    }

    @PutMapping("{id}")
    public EntityModel<Capture> update(@PathVariable Long id, @RequestBody @Valid Capture capture){

        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(()-> new RestNotFoundException("pokemon not found"));
        Trainers trainers = trainersRepository.findById(id).orElseThrow(()-> new RestNotFoundException("trainers not found"));
        captureRepository.findById(id).orElseThrow(()-> new RestNotFoundException("Capture not found"));


        capture.setId(id);
        capture.setPokemon(pokemon);
        capture.setTrainers(trainers);

        captureRepository.save(capture);

        return capture.toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Capture> destroy(@PathVariable Long id){
        var capture = captureRepository.findById(id).orElseThrow(()-> new RestNotFoundException("capture not found"));

        captureRepository.delete(capture);

        return ResponseEntity.noContent().build();
    }

}
