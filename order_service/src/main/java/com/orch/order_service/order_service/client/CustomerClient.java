package com.orch.order_service.order_service.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * Customer Service Client
 * Demonstrates inter-service communication using WebClient
 */
@Service
public class CustomerClient {

    private final WebClient customerWebClient;

    public CustomerClient(@Qualifier("customerWebClient") WebClient customerWebClient) {
        this.customerWebClient = customerWebClient;
    }

    /**
     * Get customer by ID from customer-service
     */
    public Mono<CustomerDTO> getCustomer(String customerId) {
        return customerWebClient
                .get()
                .uri("/api/customers/{customerId}", customerId)
                .retrieve()
                .bodyToMono(CustomerDTO.class);
    }

    /**
     * Check if customer exists
     */
    public boolean customerExists(String customerId) {
        try {
            return getCustomer(customerId)
                    .map(customer -> true)
                    .onErrorReturn(false)
                    .block();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Customer DTO record
     */
    public record CustomerDTO(
            Long id,
            String customerId,
            String name,
            String email,
            String phone
    ) {}
}
