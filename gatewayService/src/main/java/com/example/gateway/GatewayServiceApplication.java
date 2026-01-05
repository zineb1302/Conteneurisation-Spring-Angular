package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * API Gateway - Point d'entree unique pour les microservices
 * @author Zineb - TP25 Docker + Consul
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayServiceApplication {

    public static void main(String[] args) {
        System.out.println("=== Demarrage de la Gateway ===");
        SpringApplication.run(GatewayServiceApplication.class, args);
        System.out.println("=== Gateway active sur le port 8888 ===");
    }
}
