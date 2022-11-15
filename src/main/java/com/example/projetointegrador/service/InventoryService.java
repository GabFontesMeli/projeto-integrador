package com.example.projetointegrador.service;

import com.example.projetointegrador.service.interfaces.IInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projetointegrador.model.Inventory;
import com.example.projetointegrador.repository.InventoryRepository;

import java.nio.channels.FileLock;
import java.util.List;

@Service
public class InventoryService implements IInventoryService {
    
    @Autowired
    private InventoryRepository inventoryRepo;

    /**
     * verifica se o inventario de um produto existe: se existir chama o metodo updateInventory,
     * se não, salva um novo.
     * @param inventory
     */
    @Override
    public void saveInventory(Inventory inventory) {

        if (inventoryRepo.existsInventoryByProductId(inventory.getProduct().getId())) {
            updateInventory(inventory.getProduct().getId(),inventory.getQuantity());
        } else {
            inventoryRepo.save(inventory);
        }
    }

    /**
     * atualiza a quantidade do inventario de um produto
     * @param productId
     * @param quantity
     * @return
     */
    @Override
    public Inventory updateInventory(Long productId, Integer quantity) {
        Inventory inventoryFound = inventoryRepo.findInventoryByProductId(productId);
        Integer oldQuantity = inventoryFound.getQuantity();
        inventoryFound.setQuantity(oldQuantity + quantity);
        return inventoryRepo.save(inventoryFound);
    }

    /**
     * método que consulta o volume ocupado por storage
     * @param storageId
     * @return
     */
    @Override
    public Float findVolumeByStorage(Long storageId) {
        List<Float> volumes = inventoryRepo.findVolumeByStorage(storageId);
        return volumes.stream().reduce(0f, Float::sum);
    }
}
