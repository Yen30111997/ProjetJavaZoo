package org.edu.mimo.ProjetJava.repository;

import org.edu.mimo.ProjetJava.model.entity.AnimalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<AnimalEntity, Long> {
}
