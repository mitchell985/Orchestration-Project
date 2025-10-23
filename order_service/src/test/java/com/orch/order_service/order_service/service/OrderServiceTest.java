package com.orch.order_service.order_service.service;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.orch.order_service.order_service.model.Order;
import com.orch.order_service.order_service.model.OrderStatus;

/**
 * Unit tests for OrderService
 * Demonstrates JUnit 5 testing practices
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Order Service Tests")
class OrderServiceTest {

    private OrderService orderService;

    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() {
        orderService = new OrderService();
    }

    @Test
    @DisplayName("Should create order successfully")
    void testCreateOrder() {
        // Arrange
        String customerId = "CUST001";
        BigDecimal amount = new BigDecimal("100.00");

        // Act
        Order order = orderService.createOrder(customerId, amount);

        // Assert
        assertNotNull(order);
        assertEquals(customerId, order.customerId());
        assertEquals(amount, order.totalAmount());
        assertEquals(OrderStatus.PENDING, order.status());
        assertNotNull(order.createdAt());
    }

    @Test
    @DisplayName("Should find order by ID")
    void testFindById() {
        // Arrange
        Order createdOrder = orderService.createOrder("CUST001", new BigDecimal("50.00"));

        // Act
        var foundOrder = orderService.findById(createdOrder.id());

        // Assert
        assertTrue(foundOrder.isPresent());
        assertEquals(createdOrder.id(), foundOrder.get().id());
    }

    @Test
    @DisplayName("Should return empty for non-existent order")
    void testFindByIdNotFound() {
        // Act
        var foundOrder = orderService.findById(999L);

        // Assert
        assertTrue(foundOrder.isEmpty());
    }

    @Test
    @DisplayName("Should get all orders")
    void testGetAllOrders() {
        // Arrange
        orderService.createOrder("CUST001", new BigDecimal("100.00"));
        orderService.createOrder("CUST002", new BigDecimal("200.00"));

        // Act
        var orders = orderService.getAllOrders();

        // Assert
        assertEquals(2, orders.size());
    }

    @Test
    @DisplayName("Should filter orders by status")
    void testGetOrdersByStatus() {
        // Arrange
        orderService.createOrder("CUST001", new BigDecimal("100.00"));
        var order2 = orderService.createOrder("CUST002", new BigDecimal("200.00"));
        orderService.updateOrderStatus(order2.id(), OrderStatus.COMPLETED);

        // Act
        var pendingOrders = orderService.getOrdersByStatus(OrderStatus.PENDING);
        var completedOrders = orderService.getOrdersByStatus(OrderStatus.COMPLETED);

        // Assert
        assertEquals(1, pendingOrders.size());
        assertEquals(1, completedOrders.size());
    }

    @Test
    @DisplayName("Should calculate total revenue correctly")
    void testCalculateTotalRevenue() {
        // Arrange
        var order1 = orderService.createOrder("CUST001", new BigDecimal("100.00"));
        var order2 = orderService.createOrder("CUST002", new BigDecimal("200.00"));
        orderService.updateOrderStatus(order1.id(), OrderStatus.COMPLETED);
        orderService.updateOrderStatus(order2.id(), OrderStatus.COMPLETED);

        // Act
        BigDecimal revenue = orderService.calculateTotalRevenue();

        // Assert
        assertEquals(new BigDecimal("300.00"), revenue);
    }

    @Test
    @DisplayName("Should throw exception for negative amount")
    void testCreateOrderWithNegativeAmount() {
        // Arrange
        String customerId = "CUST001";
        BigDecimal negativeAmount = new BigDecimal("-100.00");

        // Act
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> orderService.createOrder(customerId, negativeAmount));

        // Assert
        assertEquals("Total amount cannot be negative", ex.getMessage());
    }
}
