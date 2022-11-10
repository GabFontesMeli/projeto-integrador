package com.example.projetointegrador.service.interfaces;

import com.example.projetointegrador.dto.BatchDTO;
import com.example.projetointegrador.model.Batch;

public interface IBatchService {

    Batch createBatch(BatchDTO batchDTO);
    Batch update(Long id, BatchDTO batchDTO);
}
