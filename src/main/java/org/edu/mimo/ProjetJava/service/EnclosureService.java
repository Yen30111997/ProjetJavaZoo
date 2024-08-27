package org.edu.mimo.ProjetJava.service;


import jakarta.transaction.Transactional;
import org.edu.mimo.ProjetJava.model.entity.AnimalEntity;
import org.edu.mimo.ProjetJava.model.entity.EnclosureEntity;
import org.edu.mimo.ProjetJava.model.request.AnimalRequest;
import org.edu.mimo.ProjetJava.model.request.EnclosureRequest;
import org.edu.mimo.ProjetJava.repository.AnimalRepository;
import org.edu.mimo.ProjetJava.repository.EnclosureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnclosureService {

    @Autowired
    private EnclosureRepository enclosureRepository;
    @Autowired
    private AnimalRepository animalRepository;

    public List<EnclosureEntity> getAllEnclosures() {
        return enclosureRepository.findAll();
    }

    public Optional<EnclosureEntity> getEnclosureById(Long id) {
        return enclosureRepository.findById(id);
    }

    public EnclosureEntity createEnclosure(EnclosureRequest enclosureRequest) {
        EnclosureEntity enclosure = new EnclosureEntity();
        enclosure.setName(enclosureRequest.name());
        enclosure.setLocation(enclosureRequest.location());
        return enclosureRepository.save(enclosure);
    }

    public EnclosureEntity updateEnclosure(Long id, EnclosureEntity enclosureEntityDetails) {
        EnclosureEntity enclosureEntity = enclosureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enclosure not found"));
        enclosureEntity.setName(enclosureEntityDetails.getName());
        enclosureEntity.setLocation(enclosureEntityDetails.getLocation());
        return enclosureRepository.save(enclosureEntity);
    }

    public void deleteEnclosure(Long id) {
        EnclosureEntity enclosureEntity = enclosureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enclosure not found"));
        enclosureRepository.delete(enclosureEntity);
    }
}
