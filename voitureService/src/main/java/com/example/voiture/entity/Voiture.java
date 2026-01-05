package com.example.voiture.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entite Voiture
 * @author Zineb
 */
@Entity
@Table(name = "voitures")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Voiture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "marque", nullable = false)
    private String marque;

    @Column(name = "modele")
    private String modele;

    @Column(name = "immatriculation", unique = true)
    private String immatriculation;

    @Column(name = "id_client")
    private Long clientId;
}
