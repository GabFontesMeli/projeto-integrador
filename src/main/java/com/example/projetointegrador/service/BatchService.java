package com.example.projetointegrador.service;

import com.example.projetointegrador.dto.BatchDTO;
import com.example.projetointegrador.model.Batch;
import com.example.projetointegrador.model.BatchProduct;
import com.example.projetointegrador.model.Inventory;
import com.example.projetointegrador.repository.BatchRepository;
import com.example.projetointegrador.repository.SectionRepository;
import com.example.projetointegrador.repository.StorageRepository;
import com.example.projetointegrador.service.interfaces.IBatchService;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BatchService implements IBatchService {
    
    @Autowired
    private BatchRepository repository;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private SectionRepository sectionRepo;

    @Override
    public Batch createBatch(BatchDTO batchDTO) {

        Batch batch = new Batch(batchDTO);
        
        Set<BatchProduct> batchProducts = batchDTO.getProducts();
        for (BatchProduct batchProduct : batchProducts) {
            // TODO: ver se o produto existe
            Inventory inventory = new Inventory(
                batchProduct.getQuantity(), 
                batchProduct.getProduct().getId()
            );
            inventoryService.saveInventory(inventory);
        }

        batch.setSection(sectionRepo.findById(batchDTO.getStorageId()).get());
        batch.setStorage(storageRepository.findById(batchDTO.getStorageId()).get());
        return repository.save(batch);
    }

    @Override
    public Batch update(Long id, List<BatchProduct> batchProductList) {

        if(!repository.existsById(id)){
            System.out.println("Batch doesn't exists");
            return null;
        }

        Batch batch = repository.findById(id).get();

        for (BatchProduct batchProduct : batchProductList) {
            // TODO: ver se o produto existe
            Inventory inventory = new Inventory(
                batchProduct.getQuantity(), 
                batchProduct.getProduct().getId()
            );
            inventoryService.saveInventory(inventory);
        }

        batch.addProducts(batchProductList);

        return repository.save(batch);
    }
}
