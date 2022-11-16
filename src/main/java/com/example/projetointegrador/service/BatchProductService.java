package com.example.projetointegrador.service;

import com.example.projetointegrador.model.BatchProduct;
import com.example.projetointegrador.repository.BatchProductRepository;
import com.example.projetointegrador.service.interfaces.IBatchProductService;
import org.springframework.beans.factory.annotation.Autowired;

public class BatchProductService implements IBatchProductService {

    @Autowired
    private BatchProductRepository batchProductRepository;

    @Override
    public BatchProduct getBatchProductByProductId(Long productId, Integer quantity) {
        return batchProductRepository.findBatchProductByProductId(productId, quantity);
    }
}
