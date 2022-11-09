package com.example.projetointegrador.service.interfaces;

import com.example.projetointegrador.model.Batch;

public interface IBatchService {

    Batch create(Batch batch);
    Batch update(Long id, Batch batch);
}
