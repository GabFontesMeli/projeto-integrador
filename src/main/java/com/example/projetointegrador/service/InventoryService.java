package com.example.projetointegrador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projetointegrador.model.Inventory;
import com.example.projetointegrador.repository.InventoryRepository;

@Service
public class InventoryService {
    
    @Autowired
    private InventoryRepository inventoryRepo;

    public Inventory saveInventory(Inventory inventory) {

        if (inventoryRepo.existsInventoryByProductId(inventory.getProduct().getId())) {
            Integer quantity = inventory.getQuantity();
            Inventory inventoryFound = inventoryRepo.findInventoryByProductId(inventory.getProduct().getId());
            inventoryFound.setQuantity(quantity + inventoryFound.getQuantity());
            return inventoryRepo.save(inventoryFound);
        }

        return inventoryRepo.save(inventory);
    }
}
