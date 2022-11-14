package com.example.projetointegrador.repository;

import com.example.projetointegrador.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    boolean existsInventoryByProductId(Long productId);

    Inventory findInventoryByProductId(Long productId);

}
