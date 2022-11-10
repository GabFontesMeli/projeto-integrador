package com.example.projetointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projetointegrador.model.Inventory;
import org.springframework.data.jpa.repository.Query;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    boolean existsInventoryByProductId(Long productId);

    Inventory findInventoryByProductId(Long productId);

}
