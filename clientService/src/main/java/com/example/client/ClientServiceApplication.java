package com.example.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Microservice Client - Gestion des clients
 * @author Zineb - TP25 Docker + Consul
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ClientServiceApplication {

    public static void main(String[] args) {
        System.out.println("=== Demarrage du Service Client ===");
        SpringApplication.run(ClientServiceApplication.class, args);
        System.out.println("=== Service Client actif sur le port 8088 ===");
    }
}
