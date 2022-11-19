package com.example.projetointegrador.service.interfaces;

import java.time.LocalDate;

import com.example.projetointegrador.dto.ProductDTO;
import com.example.projetointegrador.dto.ProductInBatchDTO;
import com.example.projetointegrador.exceptions.ExpiredProductException;
import com.example.projetointegrador.exceptions.ProductNotFoundException;
import com.example.projetointegrador.model.BatchProduct;

public interface IBatchProductService {

    BatchProduct getBatchProductByProductId(Long productId, Integer quantity);

    Float findVolumeBySection(Long SectionId);

    BatchProduct save(BatchProduct batchProduct);

    void verifyExpirationDate(LocalDate expirationDate) throws ExpiredProductException;

    ProductInBatchDTO findAllByProductId(Long productId);

    ProductInBatchDTO findAllByProductIdOrdered(Long productId, String order);

    ProductDTO getBatchProductsByProductIdAndStorage(Long productId) throws ProductNotFoundException;
}
