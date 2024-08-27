package org.edu.mimo.ProjetJava.controller;

import org.edu.mimo.ProjetJava.model.entity.AnimalEntity;
import org.edu.mimo.ProjetJava.model.request.AnimalRequest;
import org.edu.mimo.ProjetJava.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @GetMapping
    public List<AnimalEntity> getAllAnimals() {
        return animalService.getAllAnimals();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalEntity> getAnimalById(@PathVariable Long id) {
        Optional<AnimalEntity> animal = animalService.getAnimalById(id);
        return animal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public AnimalEntity createAnimal(@RequestBody AnimalRequest animal) {
        return animalService.saveAnimal(animal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalEntity> updateAnimal(@PathVariable Long id, @RequestBody AnimalRequest animalEntityDetails) {
        Optional<AnimalEntity> updatedAnimal = animalService.updateAnimal(animalEntityDetails, id);
        return updatedAnimal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable Long id) {
        if (animalService.getAnimalById(id).isPresent()) {
            animalService.deleteAnimal(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{animalId}/enclosures/{enclosureId}")
    public AnimalEntity linkAnimalToEnclosure(@PathVariable Long animalId, @PathVariable Long enclosureId) {
        return animalService.linkAnimalToEnclosure(animalId, enclosureId);
    }
}
