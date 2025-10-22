package com.orch.order_service.order_service.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Order record demonstrating modern Java records feature.
 * Records provide automatic implementation of constructors, getters, equals, hashCode, and toString.
 */
public record Order(
        Long id,
        String customerId,
        BigDecimal totalAmount,
        OrderStatus status,
        LocalDateTime createdAt
) {
    /**
     * Compact constructor for validation
     */
    public Order {
        if (totalAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Total amount cannot be negative");
        }
    }

    /**
     * Derived method showing optional handling
     */
    public boolean isCompleted() {
        return status == OrderStatus.COMPLETED;
    }
}
