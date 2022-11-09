package com.example.projetointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.projetointegrador.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    boolean existsInventoryByProductId(Long productId);

    Inventory findByProductId(Long productId);
}
