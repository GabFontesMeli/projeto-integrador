package com.example.projetointegrador.service;

import com.example.projetointegrador.dto.BatchDTO;
import com.example.projetointegrador.model.Batch;
import com.example.projetointegrador.model.BatchProduct;
import com.example.projetointegrador.model.Inventory;
import com.example.projetointegrador.model.Product;
import com.example.projetointegrador.repository.BatchRepository;
import com.example.projetointegrador.repository.ProductRepository;
import com.example.projetointegrador.repository.SectionRepository;
import com.example.projetointegrador.service.interfaces.IBatchService;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BatchService implements IBatchService {
    
    @Autowired
    private BatchRepository repository;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private SectionRepository sectionRepo;

    @Override
    public Batch createBatch(BatchDTO batchDTO) {

        Batch batch = new Batch(batchDTO);
        
        List<BatchProduct> batchProducts = batchDTO.getBatchProduct();
        for (BatchProduct batchProduct : batchProducts) {
            Inventory inventory = new Inventory(
                batchProduct.getQuantity(), 
                batchDTO.getStorageId(), 
                batchProduct.getProduct().getId()
            );
            inventoryService.saveInventory(inventory);
        }
        
        // // TODO: validar se o product id existe, e se o section id também existe
        // HashSet<Product> productList = new HashSet<Product>();
        // Product product = productRepo.findById(batchDTO.getProductId()).get();
        // productList.add(product);
        // batch.setProducts(productList);
        // batch.setSection(sectionRepo.findById(batchDTO.getStorageId()).get());
        return repository.save(batch);
    }

    @Override
    public Batch update(Long id, BatchDTO batchDTO) {
        if(!repository.existsById(id)){
            System.out.println("Batch doesn't exists");
            return null;
        }

        // Integer actualQuantity = batchDTO.getQuantity();
        // Integer oldQuantity = repository.findById(id).get().getQuantity();
        // Integer newQuantity = actualQuantity - oldQuantity;

        // inventoryService.updateInventory(batchDTO.getProductId(), newQuantity);

        Batch newBatch = new Batch(batchDTO);
        newBatch.setId(id);

        return repository.save(newBatch);
    }
}
