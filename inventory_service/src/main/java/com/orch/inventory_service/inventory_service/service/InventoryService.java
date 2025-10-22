package com.orch.inventory_service.inventory_service.service;

import com.orch.inventory_service.inventory_service.entity.InventoryItem;
import com.orch.inventory_service.inventory_service.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Inventory Service
 * Demonstrates service layer pattern with repository
 */
@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public InventoryItem createItem(String productId, String productName, Integer quantity) {
        var item = new InventoryItem(null, productId, productName, quantity, 0);
        return inventoryRepository.save(item);
    }

    public Optional<InventoryItem> findByProductId(String productId) {
        return inventoryRepository.findByProductId(productId);
    }

    public List<InventoryItem> getAllItems() {
        return inventoryRepository.findAll();
    }

    public boolean checkAvailability(String productId, Integer quantity) {
        return findByProductId(productId)
                .map(item -> item.isAvailable(quantity))
                .orElse(false);
    }

    public Optional<InventoryItem> updateQuantity(String productId, Integer newQuantity) {
        return findByProductId(productId)
                .map(item -> {
                    item.setQuantity(newQuantity);
                    return inventoryRepository.save(item);
                });
    }
}
