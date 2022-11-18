package com.example.projetointegrador.service.interfaces;

import com.example.projetointegrador.exceptions.ExpiredProductException;
import com.example.projetointegrador.model.BatchProduct;

import java.time.LocalDate;

public interface IBatchProductService {

    BatchProduct getBatchProductByProductId(Long productId, Integer quantity);
    Float findVolumeBySection(Long SectionId);
    BatchProduct save(BatchProduct batchProduct);
    void verifyExpirationDate(LocalDate expirationDate) throws ExpiredProductException;
}
