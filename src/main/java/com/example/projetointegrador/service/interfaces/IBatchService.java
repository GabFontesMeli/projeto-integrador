package com.example.projetointegrador.service.interfaces;

import java.util.Set;

import com.example.projetointegrador.dto.BatchDTO;
import com.example.projetointegrador.exceptions.SectionInvalidException;
import com.example.projetointegrador.model.Batch;
import com.example.projetointegrador.model.BatchProduct;

public interface IBatchService {

    Batch createBatch(BatchDTO batchDTO) throws SectionInvalidException;
    Batch update(Long id, Set<BatchProduct> batchProductList);
}
