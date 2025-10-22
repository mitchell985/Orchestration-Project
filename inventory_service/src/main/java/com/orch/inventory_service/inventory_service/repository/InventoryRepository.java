package com.orch.inventory_service.inventory_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orch.inventory_service.inventory_service.entity.InventoryItem;

/**
 * Inventory Repository
 */
@Repository
public interface InventoryRepository extends JpaRepository<InventoryItem, Long> {
    
    Optional<InventoryItem> findByProductId(String productId);
}
