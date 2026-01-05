package com.example.client.controller;

import com.example.client.entity.Client;
import com.example.client.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controleur REST Client
 * @author Zineb - TP25
 */
@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> listerClients() {
        return ResponseEntity.ok(clientService.recupererTous());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> obtenirClient(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.recupererParId(id));
    }

    @PostMapping
    public ResponseEntity<Client> creerClient(@RequestBody Client client) {
        Client nouveau = clientService.sauvegarder(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(nouveau);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerClient(@PathVariable Long id) {
        clientService.supprimer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Service Client OK - Zineb TP25");
    }
}
