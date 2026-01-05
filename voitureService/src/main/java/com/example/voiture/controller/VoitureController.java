package com.example.voiture.controller;

import com.example.voiture.entity.Voiture;
import com.example.voiture.model.VoitureResponse;
import com.example.voiture.service.VoitureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controleur REST Voiture
 * @author Zineb - TP25
 */
@RestController
@RequestMapping("/api/voitures")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class VoitureController {

    private final VoitureService voitureService;

    @GetMapping
    public ResponseEntity<List<VoitureResponse>> listerVoitures() {
        return ResponseEntity.ok(voitureService.recupererToutes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoitureResponse> obtenirVoiture(@PathVariable Long id) {
        return ResponseEntity.ok(voitureService.recupererParId(id));
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<VoitureResponse>> voituresParClient(@PathVariable Long clientId) {
        return ResponseEntity.ok(voitureService.recupererParClient(clientId));
    }

    @PostMapping
    public ResponseEntity<Voiture> creerVoiture(@RequestBody Voiture voiture) {
        Voiture nouvelle = voitureService.sauvegarder(voiture);
        return ResponseEntity.status(HttpStatus.CREATED).body(nouvelle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerVoiture(@PathVariable Long id) {
        voitureService.supprimer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Service Voiture OK - Zineb TP25");
    }
}
