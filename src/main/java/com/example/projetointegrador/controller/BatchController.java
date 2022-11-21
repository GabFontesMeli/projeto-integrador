package com.example.projetointegrador.controller;

import com.example.projetointegrador.dto.BatchDTO;
import com.example.projetointegrador.exceptions.*;
import com.example.projetointegrador.model.Batch;
import com.example.projetointegrador.model.BatchProduct;
import com.example.projetointegrador.service.interfaces.IBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1/batch")
public class BatchController {
    
    @Autowired
    private IBatchService batchService;

    /**
     * Create a new batch based in the BatchDTO parameter.
     * @param batchDTO contains the storageId and the products to be added to the batch.
     * @return The batch created.
     * @throws SectionInvalidException
     * @throws ProductNotFoundException
     * @throws CategoryInvalidException
     * @throws InsuficientVolumeException
     * @throws StorageInvalidException
     */
    @PostMapping
    public ResponseEntity<Batch> create(@RequestBody BatchDTO batchDTO) throws SectionInvalidException, ProductNotFoundException, CategoryInvalidException, InsuficientVolumeException, StorageInvalidException {
        return new ResponseEntity<>(batchService.createBatch(batchDTO), HttpStatus.CREATED);
    }

    /**
     * Update a batch by id.
     * @param id BatchId.
     * @param batchProductList List of batch products to be updated.
     * @return The batch updated.
     * @throws BatchInvalidException
     * @throws ProductNotFoundException
     * @throws InsuficientVolumeException
     * @throws SectionInvalidException
     */
    @PutMapping("/{id}")
    public ResponseEntity<Batch> update(@PathVariable Long id, @RequestBody Set<BatchProduct> batchProductList) throws BatchInvalidException, ProductNotFoundException, InsuficientVolumeException, SectionInvalidException, BatchProductNotFoundException {
        return new ResponseEntity<>(batchService.update(id, batchProductList), HttpStatus.ACCEPTED);
    }
}
