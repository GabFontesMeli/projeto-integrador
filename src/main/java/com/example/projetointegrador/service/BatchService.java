package com.example.projetointegrador.service;

import com.example.projetointegrador.dto.BatchDTO;
import com.example.projetointegrador.model.Batch;
import com.example.projetointegrador.model.Inventory;
import com.example.projetointegrador.repository.BatchRepository;
import com.example.projetointegrador.repository.ProductRepository;
import com.example.projetointegrador.repository.SectionRepository;
import com.example.projetointegrador.service.interfaces.IBatchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BatchService implements IBatchService {
    
    @Autowired
    private BatchRepository repository;

    // @Autowired
    // private InventoryRepository inventoryRepo;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private SectionRepository sectionRepo;

    @Override
    public Batch createBatch(BatchDTO batchDTO) {

        Batch batch = new Batch(batchDTO);
        
        Inventory inventory = new Inventory(
            batchDTO.getQuantity(), 
            batchDTO.getStorageId(), 
            batchDTO.getProductId()
            );
            
        inventoryService.saveInventory(inventory);

        if(repository.existsBatchByProviderBatchNumber(batch.getProviderBatchNumber())) {
            System.out.println("Existent Provider Number Batch");
            return null;
        }

        // TODO: validar se o product id existe, e se o section id também existe
        batch.setProduct(productRepo.findById(batchDTO.getProductId()).get());
        batch.setSection(sectionRepo.findById(batchDTO.getStorageId()).get());
        return repository.save(batch);
    }

    @Override
    public Batch update(Long id, Batch batch) {
        if(!repository.existsById(id)){
            System.out.println("Batch doesn't exists");
            return null;
        }
        return repository.save(batch);
    }
}
