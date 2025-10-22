package com.orch.order_service.order_service.service;

import com.orch.order_service.order_service.model.Order;
import com.orch.order_service.order_service.model.OrderStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Order service demonstrating modern Java features:
 * - var keyword for local variable type inference
 * - Streams API for functional-style operations
 * - Optionals for null-safe operations
 */
@Service
public class OrderService {

    // In-memory storage for demonstration
    private final Map<Long, Order> orders = new HashMap<>();
    private Long nextId = 1L;

    /**
     * Create a new order - demonstrates var keyword
     */
    public Order createOrder(String customerId, BigDecimal totalAmount) {
        // Using 'var' for type inference (Java 10+)
        var id = nextId++;
        var now = LocalDateTime.now();
        var order = new Order(id, customerId, totalAmount, OrderStatus.PENDING, now);
        
        orders.put(id, order);
        return order;
    }

    /**
     * Find order by ID - demonstrates Optional
     */
    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(orders.get(id));
    }

    /**
     * Get all orders - demonstrates streams and collectors
     */
    public List<Order> getAllOrders() {
        return orders.values().stream()
                .sorted(Comparator.comparing(Order::createdAt).reversed())
                .collect(Collectors.toList());
    }

    /**
     * Get orders by status - demonstrates streams with filtering
     */
    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orders.values().stream()
                .filter(order -> order.status() == status)
                .collect(Collectors.toList());
    }

    /**
     * Calculate total revenue - demonstrates streams with reduce
     */
    public BigDecimal calculateTotalRevenue() {
        return orders.values().stream()
                .filter(Order::isCompleted)
                .map(Order::totalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Get orders for customer - demonstrates streams with multiple operations
     */
    public List<Order> getCustomerOrders(String customerId) {
        return orders.values().stream()
                .filter(order -> order.customerId().equals(customerId))
                .sorted(Comparator.comparing(Order::createdAt).reversed())
                .collect(Collectors.toList());
    }

    /**
     * Update order status - demonstrates Optional with map
     */
    public Optional<Order> updateOrderStatus(Long orderId, OrderStatus newStatus) {
        return findById(orderId)
                .map(existingOrder -> {
                    // Create new order with updated status (records are immutable)
                    var updatedOrder = new Order(
                            existingOrder.id(),
                            existingOrder.customerId(),
                            existingOrder.totalAmount(),
                            newStatus,
                            existingOrder.createdAt()
                    );
                    orders.put(orderId, updatedOrder);
                    return updatedOrder;
                });
    }

    /**
     * Get order statistics - demonstrates complex stream operations
     */
    public OrderStatistics getStatistics() {
        var allOrders = orders.values();
        var totalOrders = allOrders.size();
        
        var completedOrders = allOrders.stream()
                .filter(Order::isCompleted)
                .count();
        
        var averageOrderValue = allOrders.stream()
                .map(Order::totalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(totalOrders == 0 ? 1 : totalOrders), 2, BigDecimal.ROUND_HALF_UP);
        
        return new OrderStatistics(totalOrders, completedOrders, averageOrderValue);
    }

    /**
     * Record for order statistics
     */
    public record OrderStatistics(
            int totalOrders,
            long completedOrders,
            BigDecimal averageOrderValue
    ) {}
}
