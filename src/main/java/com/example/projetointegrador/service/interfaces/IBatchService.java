package com.example.projetointegrador.service.interfaces;

import com.example.projetointegrador.dto.BatchDTO;
import com.example.projetointegrador.exceptions.BatchInvalidException;
import com.example.projetointegrador.exceptions.SectionInvalidException;
import com.example.projetointegrador.model.Batch;
import com.example.projetointegrador.model.BatchProduct;

import java.util.Set;

public interface IBatchService {

    Batch createBatch(BatchDTO batchDTO) throws SectionInvalidException;
    Batch update(Long id, Set<BatchProduct> batchProductList) throws BatchInvalidException;
}
