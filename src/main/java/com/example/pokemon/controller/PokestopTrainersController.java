package com.example.pokemon.controller;

import com.example.pokemon.exceptions.RestNotFoundException;
import com.example.pokemon.models.PokestopsTrainers;
import com.example.pokemon.repository.PokestopsRepository;
import com.example.pokemon.repository.PokestopsTrainersRepository;
import com.example.pokemon.repository.TrainersRepository;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
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

import javax.swing.text.html.parser.Entity;

@RestController
@RequestMapping("/api/pokestopsTrainers")
public class PokestopTrainersController {

    @Autowired
    PokestopsTrainersRepository pokestopsTrainersRepository;

    @Autowired
    PokestopsRepository pokestopsRepository;

    @Autowired
    TrainersRepository trainersRepository;

    @Autowired
    PagedResourcesAssembler assembler;


    @GetMapping
    public PagedModel<EntityModel<Object>> index(@PageableDefault(size = 10)Pageable pageable){
        Page<PokestopsTrainers> pokestopTrainers = pokestopsTrainersRepository.findAll(pageable);
        return assembler.toModel(pokestopTrainers.map(PokestopsTrainers::toEntityModel));
    }

    @GetMapping("{id}")
    public EntityModel<PokestopsTrainers> show(@PathVariable Long id){
        var pokestopsTrainers = pokestopsTrainersRepository.findById(id).orElseThrow(()-> new RestNotFoundException("pokestopsTrainers not found"));

        return pokestopsTrainers.toEntityModel();
    }

    @PostMapping
    public ResponseEntity<PokestopsTrainers> create(@RequestBody @Valid PokestopsTrainers pokestopsTrainers){
        var pokestop = pokestopsRepository.findById(pokestopsTrainers.getPokestops().getId())
                .orElseThrow(()-> new RestNotFoundException("pokestop not found"));

        var trainers = trainersRepository.findById(pokestopsTrainers.getTrainers().getId())
                .orElseThrow(()-> new RestNotFoundException("trainer not found"));


        pokestopsTrainers.setTrainers(trainers);
        pokestopsTrainers.setPokestops(pokestop);
        pokestopsTrainersRepository.save(pokestopsTrainers);

        return ResponseEntity.status(HttpStatus.CREATED).body(pokestopsTrainers);
    }

    @PutMapping("{id}")
    public EntityModel<PokestopsTrainers> update (@PathVariable Long id, @RequestBody @Valid PokestopsTrainers pokestopsTrainers){
        var pokestop = pokestopsRepository.findById(pokestopsTrainers.getPokestops().getId())
                .orElseThrow(()-> new RestNotFoundException("pokestop not found"));

        var trainres = trainersRepository.findById(pokestopsTrainers.getTrainers().getId())
                .orElseThrow(()-> new RestNotFoundException("trainer not found"));

        pokestopsTrainersRepository.findById(id).orElseThrow(()-> new RestNotFoundException(" pokestopTrainer not found"));

        pokestopsTrainers.setPokestops(pokestop);
        pokestopsTrainers.setTrainers(trainres);
        pokestopsTrainers.setId(id);

        pokestopsTrainersRepository.save(pokestopsTrainers);

        return pokestopsTrainers.toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<PokestopsTrainers> destroy(@PathVariable Long id){
        var pokestopTrainers = pokestopsTrainersRepository.findById(id).orElseThrow(()-> new RestNotFoundException("pokestopTrainer not found"));

        pokestopsTrainersRepository.delete(pokestopTrainers);
        return ResponseEntity.noContent().build();
    }


}
