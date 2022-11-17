package com.example.projetointegrador.service;

import com.example.projetointegrador.model.BatchProduct;
import com.example.projetointegrador.repository.BatchProductRepository;
import com.example.projetointegrador.service.interfaces.IBatchProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatchProductService implements IBatchProductService {

    @Autowired
    private BatchProductRepository batchProductRepository;

    @Override
    public BatchProduct getBatchProductByProductId(Long productId, Integer quantity) {
        return batchProductRepository.findBatchProductByProductId(productId, quantity);
    }

    @Override
    public Float findVolumeBySection(Long sectionId) {
        List<Float> volumes = batchProductRepository.findVolumeBySectionId(sectionId);
        return volumes.stream().reduce(0f,Float::sum);
    }

    @Override
    public BatchProduct save(BatchProduct batchProduct) {
        return batchProductRepository.save(batchProduct);
    }
}
