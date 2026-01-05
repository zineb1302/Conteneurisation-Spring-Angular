package com.example.voiture.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO Reponse Voiture avec Client
 * @author Zineb
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoitureResponse {
    private Long id;
    private String marque;
    private String modele;
    private String immatriculation;
    private Client client;
}
