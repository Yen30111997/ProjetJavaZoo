package org.edu.mimo.ProjetJava.service;

import org.edu.mimo.ProjetJava.model.entity.AnimalEntity;
import org.edu.mimo.ProjetJava.model.entity.EnclosureEntity;
import org.edu.mimo.ProjetJava.model.request.AnimalRequest;
import org.edu.mimo.ProjetJava.repository.AnimalRepository;
import org.edu.mimo.ProjetJava.repository.EnclosureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private EnclosureRepository enclosureRepository;

    public List<AnimalEntity> getAllAnimals() {
        return animalRepository.findAll();
    }

    public Optional<AnimalEntity> getAnimalById(Long id) {
        return animalRepository.findById(id);
    }

    public AnimalEntity saveAnimal(AnimalRequest animal) {
        AnimalEntity animalEntity = new AnimalEntity();
        return save(animal, animalEntity);
    }
    public Optional<AnimalEntity> updateAnimal(AnimalRequest animal, Long id) {
        Optional<AnimalEntity> animalOptional = getAnimalById(id);
        if (animalOptional.isPresent()) {
            AnimalEntity animalEntity = animalOptional.get();
            return Optional.of(save(animal, animalEntity));
        }
        return Optional.empty();
    }

    private AnimalEntity save(AnimalRequest animal, AnimalEntity animalEntity) {
        animalEntity.setName(animal.name());
        animalEntity.setSpecies(animal.species());
        animalEntity.setAge(animal.age());
        animalEntity.setHeight(animal.height());
        animalEntity.setLength(animal.length());
        animalEntity.setWeight(animal.weight());
        return animalRepository.save(animalEntity);
    }

    public void deleteAnimal(Long id) {
        animalRepository.deleteById(id);
    }

    public AnimalEntity linkAnimalToEnclosure(Long animalId, Long enclosureId) {
        Optional<AnimalEntity> animalOptional = animalRepository.findById(animalId);
        Optional<EnclosureEntity> enclosureOptional = enclosureRepository.findById(enclosureId);

        if (animalOptional.isPresent() && enclosureOptional.isPresent()) {
            AnimalEntity animal = animalOptional.get();
            EnclosureEntity enclosure = enclosureOptional.get();
            animal.setEnclosure(enclosure);
            return animalRepository.save(animal);
        } else {
            throw new RuntimeException("Animal or Enclosure not found");
        }
    }


}
