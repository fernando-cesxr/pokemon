package com.example.pokemon.controller;

import com.example.pokemon.exceptions.RestNotFoundException;
import com.example.pokemon.models.Trainers;
import com.example.pokemon.models.User;
import com.example.pokemon.repository.TrainersRepository;
import com.example.pokemon.repository.UserRepository;
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
@RequestMapping("/api/trainers")
public class TrainerController {
    @Autowired
    TrainersRepository trainersRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PagedResourcesAssembler assembler;

    @GetMapping
    public PagedModel<EntityModel<Object>> index(@PageableDefault(size = 10) Pageable pageable){
        Page<Trainers> trainers = trainersRepository.findAll(pageable);
        return assembler.toModel(trainers.map(Trainers::toEntityModel));
    }

    @GetMapping("/findByName")
    public PagedModel<EntityModel<Object>> searchName(@RequestParam(required = false) @PageableDefault(size = 10)
                                                      Pageable pageable, String search){
        Page<Trainers> trainers = trainersRepository.findByNameContaining(search, pageable);
        return assembler.toModel(trainers.map(Trainers::toEntityModel));
    }

    @GetMapping("{id}")
    public EntityModel<Trainers> show(@PathVariable Long id){
        var trainers = trainersRepository.findById(id).orElseThrow(() -> new RestNotFoundException("trainers not found"));

        return trainers.toEntityModel();
    }

    @PostMapping
    public ResponseEntity<Trainers> create(@RequestBody @Valid Trainers trainers){
        User user = userRepository.findById(trainers.getUser().getId()).orElseThrow(()-> new RestNotFoundException("user not found"));
        trainers.setUser(user);

        trainersRepository.save(trainers);
        return ResponseEntity.status(HttpStatus.CREATED).body(trainers);
    }

    @PutMapping("{id}")
    public EntityModel<Trainers> update(@PathVariable Long id, @RequestBody @Valid Trainers trainers){
        trainersRepository.findById(id).orElseThrow(() -> new RestNotFoundException("trainer not found"));
        User user = userRepository.findById(trainers.getUser().getId()).orElseThrow(()-> new RestNotFoundException("user not found"));
        trainers.setUser(user);
        trainers.setId(id);
        trainersRepository.save(trainers);
        return trainers.toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Trainers> destroy(@PathVariable Long id){
        var trainers = trainersRepository.findById(id).orElseThrow(()-> new RestNotFoundException("trainer not found"));

        trainersRepository.delete(trainers);

        return ResponseEntity.noContent().build();
    }

}
