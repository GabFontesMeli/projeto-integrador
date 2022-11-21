package com.example.projetointegrador.service.interfaces;

import java.time.LocalDate;
import java.util.List;

import com.example.projetointegrador.dto.ProductDTO;
import com.example.projetointegrador.dto.ProductInBatchDTO;
import com.example.projetointegrador.dto.ReportBatchProductDTO;
import com.example.projetointegrador.exceptions.ExpiredProductException;
import com.example.projetointegrador.exceptions.ProductNotFoundException;
import com.example.projetointegrador.model.BatchProduct;

public interface IBatchProductService {

    BatchProduct getBatchProductByProductId(Long productId, Integer quantity);

    Float findVolumeBySection(Long SectionId);

    BatchProduct save(BatchProduct batchProduct);

    void saveAll(List<BatchProduct> batchProducts);

    void verifyExpirationDate(LocalDate expirationDate) throws ExpiredProductException;

    ProductInBatchDTO findBatchProductsByProductId(Long productId) throws ProductNotFoundException;

    ProductInBatchDTO findBatchProductsByProductIdOrdered(Long productId, String order) throws ProductNotFoundException;

    ProductDTO getStorageQuantityByProductId(Long productId) throws ProductNotFoundException;

    ReportBatchProductDTO getBatchProductExpiring(Long days, Long sectionId);

    ReportBatchProductDTO getBatchProductExpiringOrdered(Long days, Long categoryId, String order);
}
