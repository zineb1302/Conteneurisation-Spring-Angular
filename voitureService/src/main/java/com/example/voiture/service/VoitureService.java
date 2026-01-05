package com.example.voiture.service;

import com.example.voiture.entity.Voiture;
import com.example.voiture.model.Client;
import com.example.voiture.model.VoitureResponse;
import com.example.voiture.repository.VoitureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service metier Voiture avec decouverte Consul
 * @author Zineb - TP25
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class VoitureService {

    private final VoitureRepository voitureRepository;
    private final RestTemplate restTemplate;
    private final DiscoveryClient discoveryClient;

    @Value("${client.service.url:http://localhost:8088}")
    private String clientServiceUrl;

    public List<VoitureResponse> recupererToutes() {
        log.info("Recuperation de toutes les voitures");
        return voitureRepository.findAll().stream()
                .map(this::construireResponse)
                .collect(Collectors.toList());
    }

    public VoitureResponse recupererParId(Long id) {
        log.info("Recherche de la voiture id: {}", id);
        Voiture voiture = voitureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Voiture non trouvee: " + id));
        return construireResponse(voiture);
    }

    public Voiture sauvegarder(Voiture voiture) {
        log.info("Sauvegarde de la voiture: {} {}", voiture.getMarque(), voiture.getModele());
        return voitureRepository.save(voiture);
    }

    public void supprimer(Long id) {
        log.info("Suppression de la voiture id: {}", id);
        voitureRepository.deleteById(id);
    }

    public List<VoitureResponse> recupererParClient(Long clientId) {
        log.info("Recherche des voitures du client: {}", clientId);
        return voitureRepository.findByClientId(clientId).stream()
                .map(this::construireResponse)
                .collect(Collectors.toList());
    }

    private VoitureResponse construireResponse(Voiture voiture) {
        Client client = null;
        if (voiture.getClientId() != null) {
            try {
                String url = getClientServiceUrl() + "/api/clients/" + voiture.getClientId();
                log.debug("Appel service client: {}", url);
                client = restTemplate.getForObject(url, Client.class);
            } catch (Exception e) {
                log.warn("Impossible de recuperer le client {}: {}", voiture.getClientId(), e.getMessage());
            }
        }

        return VoitureResponse.builder()
                .id(voiture.getId())
                .marque(voiture.getMarque())
                .modele(voiture.getModele())
                .immatriculation(voiture.getImmatriculation())
                .client(client)
                .build();
    }

    private String getClientServiceUrl() {
        try {
            var instances = discoveryClient.getInstances("service-client");
            if (!instances.isEmpty()) {
                String url = instances.get(0).getUri().toString();
                log.debug("Service client decouvert via Consul: {}", url);
                return url;
            }
        } catch (Exception e) {
            log.warn("Decouverte Consul echouee, utilisation URL par defaut");
        }
        return clientServiceUrl;
    }
}
