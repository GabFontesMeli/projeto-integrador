package com.example.projetointegrador.service;

import com.example.projetointegrador.dto.BatchDTO;
import com.example.projetointegrador.exceptions.BatchInvalidException;
import com.example.projetointegrador.exceptions.CategoryInvalidException;
import com.example.projetointegrador.exceptions.ProductNotFoundException;
import com.example.projetointegrador.exceptions.SectionInvalidException;
import com.example.projetointegrador.model.*;
import com.example.projetointegrador.repository.BatchRepository;
import com.example.projetointegrador.repository.SectionRepository;
import com.example.projetointegrador.repository.StorageRepository;
import com.example.projetointegrador.service.interfaces.IBatchService;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class BatchService implements IBatchService {

    // TODO: trocar as chamadas dos repositórios por serviços
    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private ProductService productService;

    /**
     * método que valida e insere um novo batch
     * @param batchDTO
     * @return
     * @throws SectionInvalidException
     */
    @Override
    public Batch createBatch(BatchDTO batchDTO) throws SectionInvalidException, ProductNotFoundException, CategoryInvalidException {

        Batch batch = new Batch(batchDTO);
        
        Set<BatchProduct> batchProducts = batchDTO.getProducts();
        for (BatchProduct batchProduct : batchProducts) {

            Product product = productService.findById(batchProduct.getProduct().getId());

            //TODO: fazer tratamento dessa exceção no sectionService.
            Section section = sectionRepository.findById(batchProduct.getSection().getId()).orElseThrow(() ->
                    new SectionInvalidException("Could not found a section with this id."));

            if(!product.getCategory().equals(section.getCategory())) throw new
                    CategoryInvalidException("The product and section categories are different.");

            Inventory inventory = new Inventory(
                batchProduct.getQuantity(), 
                batchProduct.getProduct().getId()
            );

            inventoryService.saveInventory(inventory);
        }

//        Optional<Section> sectionOptional = sectionRepository.findById(batchDTO.getSectionId());
//        if (sectionOptional.isPresent()) {
//            batch.setSection(sectionOptional.get());
//        } else {
//            throw new SectionInvalidException("section not found");
//        }

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
    @Transactional
    public Batch update(Long id, Set<BatchProduct> batchProductList) throws BatchInvalidException, ProductNotFoundException {

        if(!batchRepository.existsById(id)){
            throw new BatchInvalidException("Batch doesn't exists");
        }

        Batch batch = batchRepository.findById(id).get();

        for (BatchProduct batchProduct : batchProductList) {

            sectionRepository.findById(batchProduct.getProduct().getId()).orElseThrow(() ->
                    new ProductNotFoundException("Could not found a product with this id."));

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
