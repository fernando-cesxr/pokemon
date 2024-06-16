package com.example.pokemon.controller;

import com.example.pokemon.exceptions.RestNotFoundException;
import com.example.pokemon.models.Gym;
import com.example.pokemon.models.Trainers;
import com.example.pokemon.models.TrainersGym;
import com.example.pokemon.repository.GymRepository;
import com.example.pokemon.repository.TrainersGymRepository;
import com.example.pokemon.repository.TrainersRepository;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/trainersGyms")
public class TrainersGymController {

    @Autowired
    TrainersGymRepository trainersGymRepository;

    @Autowired
    TrainersRepository trainersRepository;

    @Autowired
    GymRepository gymRepository;

    @Autowired
    PagedResourcesAssembler assembler;


    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String search, @PageableDefault(size = 10) Pageable pageable){
        var trainersGyms = trainersGymRepository.findAll(pageable);

        return assembler.toModel(trainersGyms.map(TrainersGym::toEntityModel));
    }

    @GetMapping("{id}")
    public EntityModel<TrainersGym> show(@PathVariable Long id){
        var trainersGym = trainersGymRepository.findById(id).orElseThrow(()-> new RestNotFoundException("TrainersGym not found"));

        return trainersGym.toEntityModel();
    }

    @PostMapping
    public ResponseEntity<TrainersGym> create(@RequestBody @Valid TrainersGym trainersGym){

        Trainers trainers = trainersRepository.findById(trainersGym.getTrainers().getId())
                .orElseThrow(()-> new RestNotFoundException("trainer not found"));
        Gym gyms = gymRepository.findById(trainersGym.getGym().getId())
                .orElseThrow(()-> new RestNotFoundException("Gym not found"));

        trainersGym.setTrainers(trainers);
        trainersGym.setGym(gyms);

        trainersGymRepository.save(trainersGym);
        return ResponseEntity.status(HttpStatus.CREATED).body(trainersGym);
    }

    @PutMapping("{id}")
    public EntityModel<TrainersGym> update(@PathVariable Long id, @RequestBody @Valid TrainersGym trainersGym){
        trainersGymRepository.findById(id).orElseThrow(()-> new RestNotFoundException("TrainersGym not found"));

        Trainers trainers = trainersRepository.findById(trainersGym.getTrainers().getId())
                .orElseThrow(()-> new RestNotFoundException("trainer not found"));

        Gym gyms = gymRepository.findById(trainersGym.getGym().getId())
                .orElseThrow(()-> new RestNotFoundException("gym not found"));

        trainersGym.setGym(gyms);
        trainersGym.setTrainers(trainers);
        trainersGym.setId(id);

        return trainersGym.toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TrainersGym> destroy(@PathVariable Long id){
        var trainersGyms = trainersGymRepository.findById(id).orElseThrow(()-> new RestNotFoundException("gym not found"));

        trainersGymRepository.delete(trainersGyms);
        return ResponseEntity.noContent().build();
    }


}
