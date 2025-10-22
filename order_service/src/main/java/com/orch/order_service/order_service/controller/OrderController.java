package com.orch.order_service.order_service.controller;

import com.orch.order_service.order_service.model.Order;
import com.orch.order_service.order_service.model.OrderStatus;
import com.orch.order_service.order_service.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * REST controller for order management
 * Demonstrates modern Java features integrated with Spring Boot
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Create a new order
     */
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequest request) {
        logger.info("Creating order for customer: {}", request.customerId());
        // Using var for type inference
        var order = orderService.createOrder(request.customerId(), request.totalAmount());
        logger.info("Order created successfully: {}", order.id());
        return ResponseEntity.ok(order);
    }

    /**
     * Get order by ID - demonstrates Optional handling
     */
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        logger.debug("Fetching order with id: {}", id);
        return orderService.findById(id)
                .map(order -> {
                    logger.debug("Order found: {}", id);
                    return ResponseEntity.ok(order);
                })
                .orElseGet(() -> {
                    logger.warn("Order not found: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    /**
     * Get all orders
     */
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    /**
     * Get orders by status
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Order>> getOrdersByStatus(@PathVariable OrderStatus status) {
        return ResponseEntity.ok(orderService.getOrdersByStatus(status));
    }

    /**
     * Get orders for a specific customer
     */
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Order>> getCustomerOrders(@PathVariable String customerId) {
        return ResponseEntity.ok(orderService.getCustomerOrders(customerId));
    }

    /**
     * Update order status
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable Long id,
            @RequestBody UpdateStatusRequest request) {
        return orderService.updateOrderStatus(id, request.status())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get order statistics
     */
    @GetMapping("/statistics")
    public ResponseEntity<OrderService.OrderStatistics> getStatistics() {
        return ResponseEntity.ok(orderService.getStatistics());
    }

    /**
     * Get total revenue
     */
    @GetMapping("/revenue")
    public ResponseEntity<RevenueResponse> getTotalRevenue() {
        var revenue = orderService.calculateTotalRevenue();
        return ResponseEntity.ok(new RevenueResponse(revenue));
    }

    /**
     * Record for create order request
     */
    public record CreateOrderRequest(
            String customerId,
            BigDecimal totalAmount
    ) {}

    /**
     * Record for update status request
     */
    public record UpdateStatusRequest(
            OrderStatus status
    ) {}

    /**
     * Record for revenue response
     */
    public record RevenueResponse(
            BigDecimal totalRevenue
    ) {}
}
