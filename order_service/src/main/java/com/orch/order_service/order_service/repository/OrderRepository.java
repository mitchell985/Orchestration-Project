package com.orch.order_service.order_service.repository;

import com.orch.order_service.order_service.entity.OrderEntity;
import com.orch.order_service.order_service.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Order Repository
 * Demonstrates Spring Data JPA repository pattern
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    
    /**
     * Find orders by customer ID
     */
    List<OrderEntity> findByCustomerId(String customerId);
    
    /**
     * Find orders by status
     */
    List<OrderEntity> findByStatus(OrderStatus status);
    
    /**
     * Find completed orders by customer
     */
    List<OrderEntity> findByCustomerIdAndStatus(String customerId, OrderStatus status);
}
