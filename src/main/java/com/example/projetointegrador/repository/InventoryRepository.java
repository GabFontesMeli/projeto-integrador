package com.example.projetointegrador.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.projetointegrador.model.Inventory;

public interface InventoryRepository extends CrudRepository<Inventory, Long> {
    
}
