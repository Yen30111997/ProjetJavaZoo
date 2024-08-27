package org.edu.mimo.ProjetJava.repository;
import org.edu.mimo.ProjetJava.model.entity.EnclosureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnclosureRepository extends JpaRepository<EnclosureEntity, Long> {
}
