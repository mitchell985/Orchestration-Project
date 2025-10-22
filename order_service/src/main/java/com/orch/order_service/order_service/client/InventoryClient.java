package com.orch.order_service.order_service.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Inventory Service Client
 * Demonstrates inter-service communication using WebClient
 */
@Service
public class InventoryClient {

    private final WebClient inventoryWebClient;

    public InventoryClient(@Qualifier("inventoryWebClient") WebClient inventoryWebClient) {
        this.inventoryWebClient = inventoryWebClient;
    }

    /**
     * Check product availability
     */
    public Mono<AvailabilityResponse> checkAvailability(String productId, Integer quantity) {
        return inventoryWebClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/inventory/{productId}/available")
                        .queryParam("quantity", quantity)
                        .build(productId))
                .retrieve()
                .bodyToMono(AvailabilityResponse.class);
    }

    /**
     * Check if product is available (blocking)
     */
    public boolean isProductAvailable(String productId, Integer quantity) {
        try {
            return checkAvailability(productId, quantity)
                    .map(AvailabilityResponse::available)
                    .onErrorReturn(false)
                    .block();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Availability Response DTO record
     */
    public record AvailabilityResponse(
            String productId,
            Integer requestedQuantity,
            boolean available
    ) {}
}
