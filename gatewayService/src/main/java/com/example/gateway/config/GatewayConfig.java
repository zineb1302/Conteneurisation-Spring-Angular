package com.example.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration des routes de la Gateway
 * @author Zineb - TP25
 */
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // Route vers le service Client
                .route("service-client", r -> r
                        .path("/api/clients/**")
                        .uri("lb://service-client"))
                // Route vers le service Voiture
                .route("service-voiture", r -> r
                        .path("/api/voitures/**")
                        .uri("lb://service-voiture"))
                .build();
    }
}
