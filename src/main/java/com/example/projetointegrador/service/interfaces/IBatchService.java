package com.example.projetointegrador.service.interfaces;

import com.example.projetointegrador.dto.BatchDTO;
import com.example.projetointegrador.exceptions.*;
import com.example.projetointegrador.model.Batch;
import com.example.projetointegrador.model.BatchProduct;
import com.example.projetointegrador.model.Section;

import java.util.Map;
import java.util.Set;

public interface IBatchService {

    Batch createBatch(BatchDTO batchDTO) throws SectionInvalidException, ProductNotFoundException, CategoryInvalidException, InsuficientVolumeException, StorageInvalidException;
    Batch update(Long id, Set<BatchProduct> batchProductList) throws BatchInvalidException, ProductNotFoundException, SectionInvalidException, InsuficientVolumeException;

    void hasRemainigVolume(Map<Section, Float> sectionsVolumes) throws InsuficientVolumeException;

    Batch findById(Long id) throws BatchInvalidException;
}
