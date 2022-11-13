package com.example.projetointegrador.service.interfaces;

import com.example.projetointegrador.model.Inventory;

public interface IInventoryService {
    
    void saveInventory(Inventory inventory);

    Inventory updateInventory(Long productId, Integer quantity);
}
