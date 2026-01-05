package com.example.client.service;

import com.example.client.entity.Client;
import com.example.client.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service metier Client
 * @author Zineb
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {

    private final ClientRepository clientRepository;

    public List<Client> recupererTous() {
        log.info("Recuperation de tous les clients");
        return clientRepository.findAll();
    }

    public Client recupererParId(Long id) {
        log.info("Recherche du client id: {}", id);
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client non trouve: " + id));
    }

    public Client sauvegarder(Client client) {
        log.info("Sauvegarde du client: {}", client.getNom());
        return clientRepository.save(client);
    }

    public void supprimer(Long id) {
        log.info("Suppression du client id: {}", id);
        clientRepository.deleteById(id);
    }
}
