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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;


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

    /**
     * método que valida e insere um novo batch
     * @param batchDTO
     * @return
     * @throws SectionInvalidException
     */

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

        batch.setStorage(
                storageRepository.findById(batchDTO.getStorageId()).get());
        return batchRepository.save(batch);
    }

    /***
     * método que atualiza o batch existente
     * @param id
     * @param batchProductList
     * @return
     */
    @Override
    public Batch update(Long id, Set<BatchProduct> batchProductList) throws BatchInvalidException {

        if(!batchRepository.existsById(id)){
            throw new BatchInvalidException("Batch doesn't exists");
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
