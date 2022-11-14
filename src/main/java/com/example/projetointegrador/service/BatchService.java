package com.example.projetointegrador.service;

import com.example.projetointegrador.dto.BatchDTO;
import com.example.projetointegrador.exceptions.BatchInvalidException;
import com.example.projetointegrador.exceptions.SectionInvalidException;
import com.example.projetointegrador.model.Batch;
import com.example.projetointegrador.model.BatchProduct;
import com.example.projetointegrador.model.Inventory;
import com.example.projetointegrador.model.Section;
import com.example.projetointegrador.repository.BatchRepository;
import com.example.projetointegrador.repository.SectionRepository;
import com.example.projetointegrador.repository.StorageRepository;
import com.example.projetointegrador.service.interfaces.IBatchService;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BatchService implements IBatchService {
    
    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private SectionRepository sectionRepository;

<<<<<<< HEAD
    /**
     * método que valida e insere um novo batch
     * @param batchDTO
     * @return
     * @throws SectionInvalidException
     */
=======
>>>>>>> origin/feature/requirement2
    @Override
    public Batch createBatch(BatchDTO batchDTO) throws SectionInvalidException {

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
        // TODO: mudar esse tratamento para o serviço de section
        Optional<Section> sectionOptional = sectionRepository.findById(batchDTO.getSectionId());
        if (sectionOptional.isPresent()) {
            batch.setSection(sectionOptional.get());
        } else {
            throw new SectionInvalidException("section not found");
        }

<<<<<<< HEAD
        batch.setStorage(
                storageRepository.findById(batchDTO.getStorageId()).get());
        return repository.save(batch);
=======
        batch.setSection(sectionRepository.findById(batchDTO.getStorageId()).get());
        batch.setStorage(storageRepository.findById(batchDTO.getStorageId()).get());
        return batchRepository.save(batch);
>>>>>>> origin/feature/requirement2
    }

    /***
     * método que atualiza o batch existente
     * @param id
     * @param batchProductList
     * @return
     */
    @Override
    public Batch update(Long id, Set<BatchProduct> batchProductList) throws BatchInvalidException {

<<<<<<< HEAD
        if(!repository.existsById(id)){
            throw new BatchInvalidException("Batch doesn't exists");
=======
        if(!batchRepository.existsById(id)){
            System.out.println("Batch doesn't exists");
            return null;
>>>>>>> origin/feature/requirement2
        }

        Batch batch = batchRepository.findById(id).get();

        for (BatchProduct batchProduct : batchProductList) {
            // TODO: ver se o produto existe
            Inventory inventory = new Inventory(
                batchProduct.getQuantity(), 
                batchProduct.getProduct().getId()
            );
            inventoryService.saveInventory(inventory);
        }

        batch.addProducts(batchProductList);

        return batchRepository.save(batch);
    }
}
