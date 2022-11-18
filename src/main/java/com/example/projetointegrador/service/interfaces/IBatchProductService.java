package com.example.projetointegrador.service.interfaces;

import com.example.projetointegrador.dto.ProductInBatchDTO;
import com.example.projetointegrador.model.BatchProduct;

import java.util.List;

public interface IBatchProductService {

    BatchProduct getBatchProductByProductId(Long productId, Integer quantity);
    Float findVolumeBySection(Long SectionId);

    BatchProduct save(BatchProduct batchProduct);
//    ProductInBatchDTO getBatchProductsByProductId(Long productId);

    ProductInBatchDTO findAllByProductId(Long productId);
}
