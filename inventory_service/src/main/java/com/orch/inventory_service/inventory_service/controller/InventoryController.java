package com.orch.inventory_service.inventory_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orch.inventory_service.inventory_service.entity.InventoryItem;
import com.orch.inventory_service.inventory_service.service.InventoryService;

/**
 * Inventory REST Controller
 * Demonstrates CRUD operations with REST endpoints
 */
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /**
     * Create a new inventory item
     */
    @PostMapping
    public ResponseEntity<InventoryItem> createItem(@RequestBody CreateItemRequest request) {
        var item = inventoryService.createItem(
            request.productId(),
            request.productName(),
            request.quantity()
        );
        return ResponseEntity.ok(item);
    }

    /**
     * Get item by product ID
     */
    @GetMapping("/{productId}")
    public ResponseEntity<InventoryItem> getItem(@PathVariable String productId) {
        return inventoryService.findByProductId(productId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get all inventory items
     */
    @GetMapping
    public ResponseEntity<List<InventoryItem>> getAllItems() {
        return ResponseEntity.ok(inventoryService.getAllItems());
    }

    /**
     * Check availability of a product
     */
    @GetMapping("/{productId}/available")
    public ResponseEntity<AvailabilityResponse> checkAvailability(
            @PathVariable String productId,
            @RequestParam Integer quantity) {
        var available = inventoryService.checkAvailability(productId, quantity);
        return ResponseEntity.ok(new AvailabilityResponse(productId, quantity, available));
    }

    /**
     * Update inventory quantity
     */
    @PatchMapping("/{productId}/quantity")
    public ResponseEntity<InventoryItem> updateQuantity(
            @PathVariable String productId,
            @RequestBody UpdateQuantityRequest request) {
        return inventoryService.updateQuantity(productId, request.quantity())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Request DTOs records
     */
    public record CreateItemRequest(
            String productId,
            String productName,
            Integer quantity
    ) {}

    public record UpdateQuantityRequest(
            Integer quantity
    ) {}

    public record AvailabilityResponse(
            String productId,
            Integer requestedQuantity,
            boolean available
    ) {}
}
