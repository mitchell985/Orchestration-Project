package com.orch.customer_service.customer_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orch.customer_service.customer_service.entity.Customer;
import com.orch.customer_service.customer_service.service.CustomerService;

/**
 * Customer REST Controller
 * Demonstrates CRUD operations with REST endpoints
 */
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Create a new customer
     */
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody CreateCustomerRequest request) {
        var customer = customerService.createCustomer(
            request.customerId(),
            request.name(),
            request.email(),
            request.phone()
        );
        return ResponseEntity.ok(customer);
    }

    /**
     * Get customer by ID
     */
    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomer(@PathVariable String customerId) {
        return customerService.findByCustomerId(customerId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get customer by email
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email) {
        return customerService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get all customers
     */
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    /**
     * Update customer
     */
    @PutMapping("/{customerId}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable String customerId,
            @RequestBody UpdateCustomerRequest request) {
        return customerService.updateCustomer(
                customerId,
                request.name(),
                request.email(),
                request.phone()
        )
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Delete customer
     */
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String customerId) {
        var deleted = customerService.deleteCustomer(customerId);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    /**
     * Request DTOs using records
     */
    public record CreateCustomerRequest(
            String customerId,
            String name,
            String email,
            String phone
    ) {}

    public record UpdateCustomerRequest(
            String name,
            String email,
            String phone
    ) {}
}
