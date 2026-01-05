package com.example.voiture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Microservice Voiture - Gestion des vehicules
 * @author Zineb - TP25 Docker + Consul
 */
@SpringBootApplication
@EnableDiscoveryClient
public class VoitureServiceApplication {

    public static void main(String[] args) {
        System.out.println("=== Demarrage du Service Voiture ===");
        SpringApplication.run(VoitureServiceApplication.class, args);
        System.out.println("=== Service Voiture actif sur le port 8089 ===");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
