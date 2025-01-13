package com.proyecto.adopcion.controller;

import com.proyecto.adopcion.model.Pet;
import com.proyecto.adopcion.service.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mascotas")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping
    public List<Pet> getAllPets() {
        return petService.getAllPet();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable Long id) {
        Optional<Pet> pet = petService.getPetById(id);
        return pet.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/disponible")
    public ResponseEntity<List<Pet>> getPetAvailable() {
        List<Pet> pets = petService.getPetAvailable();

        if (pets.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(pets);
    }

    @PostMapping
    public ResponseEntity<Object> createPet(@RequestBody @Valid Pet pet, BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        Pet createPet = petService.createPet(pet);

        return ResponseEntity.ok(createPet);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable Long id, @RequestBody @Valid Pet pet) {

        try {
            Pet petUpdate = petService.updatePet(id, pet);
            return ResponseEntity.ok(petUpdate);

        } catch (RuntimeException e) {
            return ResponseEntity.noContent().build();
        }

    }

    @PatchMapping("/{id}")
    public ResponseEntity<Pet> parcialUpdatePet(@PathVariable Long id, @RequestBody Pet pet) {

        try {
            Pet petUpdate = petService.parcialUpdatePet(id, pet);
            return ResponseEntity.ok(petUpdate);
        } catch (RuntimeException e) {
            return ResponseEntity.noContent().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        petService.deletePet(id);
        return ResponseEntity.noContent().build();
    }

}
