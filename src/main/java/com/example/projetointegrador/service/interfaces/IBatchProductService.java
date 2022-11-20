package com.example.projetointegrador.service.interfaces;

import com.example.projetointegrador.dto.ProductDTO;
import com.example.projetointegrador.dto.ProductInBatchDTO;
import com.example.projetointegrador.dto.ReportBatchProductDTO;
import com.example.projetointegrador.exceptions.BatchProductNotFoundException;
import com.example.projetointegrador.exceptions.ExpiredProductException;
import com.example.projetointegrador.exceptions.ProductNotFoundException;
import com.example.projetointegrador.model.BatchProduct;

import java.time.LocalDate;
import java.util.List;

public interface IBatchProductService {

    BatchProduct getBatchProductByProductId(Long productId, Integer quantity);

    Float findVolumeBySection(Long SectionId);

    BatchProduct save(BatchProduct batchProduct);

    void saveAll(List<BatchProduct> batchProducts);

    void verifyExpirationDate(LocalDate expirationDate) throws ExpiredProductException;

    ProductInBatchDTO findBatchProductsByProductId(Long productId);

    ProductInBatchDTO findBatchProductsByProductIdOrdered(Long productId, String order);

    ProductDTO getStorageQuantityByProductId(Long productId) throws ProductNotFoundException;

    ReportBatchProductDTO getBatchProductExpiring(Long days, Long sectionId);

    ReportBatchProductDTO getBatchProductExpiringOrdered(Long days, Long categoryId, String order);

    BatchProduct getBatchProductByProductIdAndBatchId(Long productId, Long batchId) throws BatchProductNotFoundException;
}
