package com.orch.customer_service.customer_service.service;

import com.orch.customer_service.customer_service.entity.Customer;
import com.orch.customer_service.customer_service.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Customer Service
 * Demonstrates service layer pattern with repository
 */
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(String customerId, String name, String email, String phone) {
        var customer = new Customer(null, customerId, name, email, phone, null);
        return customerRepository.save(customer);
    }

    public Optional<Customer> findByCustomerId(String customerId) {
        return customerRepository.findByCustomerId(customerId);
    }

    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> updateCustomer(String customerId, String name, String email, String phone) {
        return findByCustomerId(customerId)
                .map(customer -> {
                    customer.setName(name);
                    customer.setEmail(email);
                    customer.setPhone(phone);
                    return customerRepository.save(customer);
                });
    }

    public boolean deleteCustomer(String customerId) {
        return findByCustomerId(customerId)
                .map(customer -> {
                    customerRepository.delete(customer);
                    return true;
                })
                .orElse(false);
    }
}
