package com.example.projetointegrador.service.interfaces;

import com.example.projetointegrador.exceptions.ExpiredProductException;
import com.example.projetointegrador.model.BatchProduct;

import java.time.LocalDate;
import java.util.List;

public interface IBatchProductService {

    BatchProduct getBatchProductByProductId(Long productId, Integer quantity);
    Float findVolumeBySection(Long SectionId);
    BatchProduct save(BatchProduct batchProduct);
    void saveAll(List<BatchProduct> batchProducts);
    void verifyExpirationDate(LocalDate expirationDate) throws ExpiredProductException;
}
