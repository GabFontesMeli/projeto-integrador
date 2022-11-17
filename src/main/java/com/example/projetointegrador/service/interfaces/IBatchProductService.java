package com.example.projetointegrador.service.interfaces;

import com.example.projetointegrador.model.BatchProduct;

public interface IBatchProductService {

    BatchProduct getBatchProductByProductId(Long productId, Integer quantity);
    Float findVolumeBySection(Long SectionId);

    BatchProduct save(BatchProduct batchProduct);
}
