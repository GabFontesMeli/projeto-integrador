package com.example.projetointegrador.service;

import com.example.projetointegrador.exceptions.ExpiredProductException;
import com.example.projetointegrador.model.BatchProduct;
import com.example.projetointegrador.repository.BatchProductRepository;
import com.example.projetointegrador.service.interfaces.IBatchProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
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

    @Override
    public void verifyExpirationDate(LocalDate expirationDate) throws ExpiredProductException {
        LocalDate today = LocalDate.now();
        Period difference = Period.between(today, expirationDate);

        if (difference.getDays() < 21) {
            throw new ExpiredProductException("expired product");
        }
    }
}
