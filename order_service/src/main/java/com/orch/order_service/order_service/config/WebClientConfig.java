package com.orch.order_service.order_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * WebClient Configuration
 * Demonstrates configuration of WebClient for inter-service communication
 */
@Configuration
public class WebClientConfig {

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebClient inventoryWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl("http://localhost:8082")
                .build();
    }

    @Bean
    public WebClient customerWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl("http://localhost:8083")
                .build();
    }
}
