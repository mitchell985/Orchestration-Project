package com.orch.order_service.order_service.controller;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Health check controller * Health check controller
 */

@RestController
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        var healthStatus = Map.of(
            "status", "UP",
            "service", "order-service",
            "timestamp", LocalDateTime.now().toString(),
            "version", "0.0.1-SNAPSHOT"
        );

        return ResponseEntity.ok(healthStatus);
    }
}
