package com.example.voiture.repository;

import com.example.voiture.entity.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository Voiture
 * @author Zineb
 */
@Repository
public interface VoitureRepository extends JpaRepository<Voiture, Long> {
    List<Voiture> findByClientId(Long clientId);
    List<Voiture> findByMarqueIgnoreCase(String marque);
}
