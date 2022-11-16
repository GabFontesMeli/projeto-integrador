package com.example.projetointegrador.service;

import com.example.projetointegrador.dto.BatchDTO;
import com.example.projetointegrador.dto.CartItemDTO;
import com.example.projetointegrador.exceptions.*;
import com.example.projetointegrador.model.*;
import com.example.projetointegrador.repository.BatchRepository;
import com.example.projetointegrador.repository.SectionRepository;
import com.example.projetointegrador.repository.StorageRepository;
import com.example.projetointegrador.service.interfaces.IBatchService;

import java.util.ArrayList;
import java.util.List;
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
    private StorageService storageService;

    @Autowired
    private SectionService sectionService;


    @Autowired
    private ProductService productService;

    /**
     * método que valida e insere um novo batch
     * @param batchDTO
     * @return
     * @throws SectionInvalidException
     */
    @Override
    public Batch createBatch(BatchDTO batchDTO) throws SectionInvalidException, ProductNotFoundException, CategoryInvalidException, InsuficientVolumeException, StorageInvalidException {
        Batch batch = new Batch(batchDTO);

        Storage storage = storageService.findById(batchDTO.getStorageId());
        Float usedVolume = inventoryService.findVolumeByStorage(batchDTO.getStorageId());
        Float expectedVolume = 0f;

        List<Inventory> newInventorys = new ArrayList<>();
        Set<BatchProduct> batchProducts = batchDTO.getProducts();

        for (BatchProduct batchProduct : batchProducts) {
            Product product = productService.findById(batchProduct.getProduct().getId());

            Section section = sectionService.findById(batchProduct.getSection().getId());


            if(!product.getCategory().equals(section.getCategory())) throw new
                    CategoryInvalidException("The product and section categories are different.");

            Inventory inventory = new Inventory(
                batchProduct.getQuantity(),
                batchProduct.getProduct().getId()
            );
            newInventorys.add(inventory);
            expectedVolume += product.getVolume() * inventory.getQuantity();
        }
        if(expectedVolume > (storage.getVolume() - usedVolume)) {
            throw new InsuficientVolumeException("expected volume not found");
        }

        newInventorys.forEach(i -> {
            inventoryService.saveInventory(i);
        });

        batch.setStorage(storage);
        return batchRepository.save(batch);
    }

    /***
     * método que atualiza o batch existente
     * @param id
     * @param batchProductList
     * @return
     */
    @Override
    public Batch update(Long id, Set<BatchProduct> batchProductList) throws BatchInvalidException, ProductNotFoundException {

        if(!batchRepository.existsById(id)){
            throw new BatchInvalidException("Batch doesn't exists");
        }

        Batch batch = batchRepository.findById(id).get();

        for (BatchProduct batchProduct : batchProductList) {

            productService.findById(batchProduct.getProduct().getId());

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
