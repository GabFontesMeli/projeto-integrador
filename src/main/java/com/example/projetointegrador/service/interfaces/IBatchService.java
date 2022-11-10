package com.example.projetointegrador.service.interfaces;

import java.util.List;

import com.example.projetointegrador.dto.BatchDTO;
import com.example.projetointegrador.model.Batch;
import com.example.projetointegrador.model.BatchProduct;

public interface IBatchService {

    Batch createBatch(BatchDTO batchDTO);
    Batch update(Long id, List<BatchProduct> batchProductList);
}
