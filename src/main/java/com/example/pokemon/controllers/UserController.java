package com.example.pokemon.controllers;

import com.example.pokemon.exceptions.RestNotFoundException;
import com.example.pokemon.models.Credencial;
import com.example.pokemon.models.User;
import com.example.pokemon.repository.UserRepository;
import com.example.pokemon.service.TokenJwtService;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    PagedResourcesAssembler assembler;

    @Autowired
    TokenJwtService tokenJwtService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid User user){
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid Credencial credencial){
        manager.authenticate(credencial.toAuthetication());
        var token = tokenJwtService.generateToken(credencial);
        return ResponseEntity.ok(token);
    }

    @GetMapping
    public PagedModel<EntityModel<Object>>index(@RequestParam(required = false) String email, @PageableDefault(size = 10) Pageable pageable){
        if (email != null){
            Page<User> user = userRepository.findByEmailContaining(email, pageable);
            return assembler.toModel(user.map(User::toEntityModel));
        }
        Page<User> user = userRepository.findAll(pageable);
        return assembler.toModel(user.map(User::toEntityModel));

    }

    @GetMapping("{id}")
    public EntityModel<User> show(@PathVariable Long id){
        var user = userRepository.findById(id).orElseThrow(() -> new RestNotFoundException("user not found"));
        return user.toEntityModel();
    }

    @PutMapping("{id}")
    public EntityModel<User> update( @PathVariable Long id, @RequestBody @Valid User user){
        userRepository.findById(id).orElseThrow(()-> new RestNotFoundException("user not found"));
        userRepository.save(user);
        return user.toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<User> destroy(@PathVariable Long id){
        var user = userRepository.findById(id).orElseThrow(()-> new RestNotFoundException("user not found"));
        userRepository.delete(user);
        return ResponseEntity.noContent().build();
    }






}
