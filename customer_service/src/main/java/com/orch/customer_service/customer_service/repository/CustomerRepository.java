package com.orch.customer_service.customer_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orch.customer_service.customer_service.entity.Customer;

/**
 * Customer Repository
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    Optional<Customer> findByCustomerId(String customerId);
    
    Optional<Customer> findByEmail(String email);
}
